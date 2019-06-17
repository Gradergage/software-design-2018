package utils;

import java.sql.*;

public class DBUtils {
    private Connection connection;
    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
            String host = "ec2-54-75-230-253.eu-west-1.compute.amazonaws.com";
            int port = 5432;
            String user = "ostspxqdcrdeer";
            String password = "66c2d2259b601e7791847219a418607cf0c94144d5f182ce71e5d15c41d6a923";
            String dbname = "d8mqpf0vhbqg9e";
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
