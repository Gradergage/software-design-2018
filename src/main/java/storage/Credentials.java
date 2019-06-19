package storage;

import java.io.*;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Credentials {
    private static String host;
    private static String password;
    private static String dbName;
    private static Credentials instance;
    private static long port;
    private static String user;

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        Credentials.host = host;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Credentials.password = password;
    }

    public static String getDbName() {
        return dbName;
    }

    public static void setDbName(String dbName) {
        Credentials.dbName = dbName;
    }

    public static long getPort() {
        return port;
    }

    public static void setPort(long port) {
        Credentials.port = port;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Credentials.user = user;
    }

    public static void init() {
        System.out.println("INIT STARTED");
        try {
            FileReader reader = new FileReader("creds.json");
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(reader);
            setHost((String) obj.get("host"));
            setPort((Long)obj.get("port"));
            setUser((String) obj.get("user"));
            setDbName((String) obj.get("dbName"));
            setPassword((String) obj.get("password"));
            System.out.println(obj.toJSONString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
