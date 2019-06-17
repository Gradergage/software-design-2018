package mappers;

import model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class AddressMapper {
    public static final String COLUMNS = "id, address";
    private HashMap<Long, Address> loadedMap;
    private Connection connection;

    public AddressMapper(Connection connection) {
        this.loadedMap = new HashMap<>();
        this.connection = connection;
    }

    private String findStatement() {
        return "SELECT address_id FROM addresses WHERE address = ?";
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

    private void doInsert(Address address, PreparedStatement insertStatement) throws SQLException {
        insertStatement.setString(1, address.getAddress());
    }
}
