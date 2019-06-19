package utils;

import storage.Credentials;

import java.sql.*;

public class DBUtils {
    private Connection connection;
    public void init() {
        try {
            Credentials.init();
            Class.forName("org.postgresql.Driver");
            String host = Credentials.getHost();
            long port = Credentials.getPort();
            String user = Credentials.getUser();
            String password = Credentials.getPassword();
            String dbname = Credentials.getDbName();
            connection = DriverManager.getConnection(String.format("jdbc:postgresql://%s:%d/%s?user=%s&password=%s",
                    host, port, dbname, user, password));
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failure");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
