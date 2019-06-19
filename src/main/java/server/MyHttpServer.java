package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import storage.TariffsMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MyHttpServer {
    public static volatile boolean started = false;
    private static HttpServer  server;
    TariffsMapper tariffs;

    public void setTariffs(TariffsMapper tariffs) {
        this.tariffs = tariffs;
    }

    public MyHttpServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/tariffs", new HttpServerHandler());
            server.setExecutor(null); // creates a default executor

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void start() {
        server.start();
    }

    public static void stop() {
        server.stop(0);
    }

    class HttpServerHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = tariffs.getTariffsJson();
            System.out.println(response);
            t.sendResponseHeaders(200, 0);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes("Windows-1251"));
            os.close();
        }
    }
}
