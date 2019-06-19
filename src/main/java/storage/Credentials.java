package storage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

import java.io.*;

public class Credentials {
    private static String host;
    private static String password;
    private static String dbName;
    private static Credentials instance;
    private static int port;
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

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Credentials.port = port;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Credentials.user = user;
    }

    public static void init() {
        try {
            InputStream is = new FileInputStream("creds.json");

            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            /*StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String fileAsString = sb.toString();
            Gson gson = new Gson();
            instance = gson.fromJson(fileAsString,Credentials.class);*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
