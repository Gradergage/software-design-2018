package storage;

import model.Address;

import java.sql.*;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class TariffsMapper {
    public static final String COLUMNS = "id, address";
    private HashMap<Long, Address> loadedMap;
    private Connection connection;
    private String allStatement = "SELECT * FROM tariffs";

    public TariffsMapper(Connection connection) {
        this.loadedMap = new HashMap<>();
        this.connection = connection;
    }

    private String findStatement() {
        return "SELECT * FROM tariffs WHERE id = ?";
    }

    private String insertStatement() {
        return "INSERT INTO addresses (address) VALUES (?) RETURNING id";
    }

    public Address find(String addressString) throws SQLException {
        Long address_id = null;
        try {
            address_id = loadedMap.entrySet().stream()
                    .filter(x -> x.getValue().getAddress().equals(addressString)).findFirst().get().getKey();
        } catch (NoSuchElementException e) {
            System.out.println("No value presented");
        }

        if (address_id != null)
            return loadedMap.get(address_id);
        PreparedStatement findStatement;
        try {
            findStatement = connection.prepareStatement(findStatement());
            findStatement.setString(1, addressString);
            ResultSet resultSet = findStatement.executeQuery();
            resultSet.next();
            address_id = resultSet.getLong(1);
            Address address = new Address();
            address.setAddress(addressString);
            address.setId(address_id);
            loadedMap.put(address_id, address);
            return address;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Long insert(Address address) throws SQLException {
        PreparedStatement insertStatement = null;
        try {
            insertStatement = connection.prepareStatement(insertStatement());
            doInsert(address, insertStatement);
            ResultSet item_id = insertStatement.executeQuery();
            item_id.next();
            loadedMap.put(item_id.getLong(1), address);
            return item_id.getLong(1);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public String getTariffsJson() {
        String result = "{ \"tariffs\": [%s] }";
        try {
            PreparedStatement query = connection.prepareStatement(allStatement);
            ResultSet resultSet = query.executeQuery();
            String tariffsArray = "";
            while (resultSet.next()) {

                String tariff = "{\"id\":%s," +
                        "\"name\":\"%s\"," +
                        "\"price\":%s}";
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                tariffsArray = tariffsArray + String.format(tariff, id, name, price) + ",";
            }
            tariffsArray = tariffsArray.substring(0, tariffsArray.length() - 1);
            result = String.format(result, tariffsArray);
        } catch (SQLException e) {
            e.printStackTrace();
        }
      //  System.out.println(result);
        return result;
    }

    private void doInsert(Address address, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setString(1, address.getAddress());
    }
}
