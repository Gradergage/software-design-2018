
import utils.DBUtils;

import java.sql.*;
public class Main {
    public static void main(String[] args) {
        DBUtils dbUtils = new DBUtils();
        try {
            Statement statement = dbUtils.getConnection().createStatement();
            System.out.printf("%-5.5s %-20.30s %-30.30s%n","id","name", "price");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.devices");
            while (resultSet.next()) {
                System.out.printf("%-5.5s %-20.30s %-30.30s%n", resultSet.getString("id"),resultSet.getString("name"), resultSet.getString("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

          /*  Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpServerHelper server = new HttpServerHelper();
                server.start();
            }
        });
        serverThread.start();

        System.out.println("HelloWorld");
        serverThread.join();*/

   /*     try {
          //  Class.forName("org.postgresql.Driver");
           // Class.forName("com.example.jdbc.Driver");
            Class.forName("org.postgresql.Driver");
            String host="ec2-54-75-230-253.eu-west-1.compute.amazonaws.com";
            int port=5432;
            String user="ostspxqdcrdeer";
            String password="66c2d2259b601e7791847219a418607cf0c94144d5f182ce71e5d15c41d6a923";
            String dbname="d8mqpf0vhbqg9e";
           //String dbAddress="postgres://ostspxqdcrdeer:66c2d2259b601e7791847219a418607cf0c94144d5f182ce71e5d15c41d6a923@ec2-54-75-230-253.eu-west-1.compute.amazonaws.com:5432/d8mqpf0vhbqg9e";
            //jdbc:postgresql://<host>:<port>/<dbname>?user=<username>&password=<password>
            Connection connection = DriverManager.getConnection(String.format("jdbc:postgresql://%s:%d/%s?user=%s&password=%s",
                    host,port,dbname,user,password));
            // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
            // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
//          ;

            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            System.out.println("Reading car records...");


        } *//*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*//* catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }*/
}