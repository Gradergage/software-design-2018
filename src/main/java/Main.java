import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storage.TariffsMapper;
import server.MyHttpServer;
import utils.DBUtils;
import utils.HibernateUtils;

public class Main extends Application {
    public static void main(String[] args) {
     /*   Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                DBUtils utils = new DBUtils();
                utils.init();
                TariffsMapper tariffs = new TariffsMapper(utils.getConnection());
                MyHttpServer server = new MyHttpServer();
                server.setTariffs(tariffs);
                MyHttpServer.start();
            }
        });
        serverThread.start();*/

        launch(args);
      /*  MyHttpServer.stop();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HibernateUtils.setUp();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Authorize");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}