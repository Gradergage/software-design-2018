package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import storage.Users;
import users.Customer;
import users.OperatorCC;
import users.OperatorTC;
import users.OperatorWC;

import java.io.IOException;

public class LoginController {

    public Button loginButton;
    public PasswordField passwordField;
    public TextField loginField;

    public void login() {

        String login = loginField.getText();
        String password = passwordField.getText();

        if (login != null && password != null) {
            User user = Users.getByLogin(login);

            if (user != null) {
                System.out.println(user.getType());
                switch (user.getType()) {
                    case 0: {
                        OperatorCC op = new OperatorCC(user);
                        CurrentUser.setCurrentUser(op);
                        loadScene("/fxml/cc_orders_view.fxml");
                        break;
                    }
                    case 2: {
                        OperatorTC cus = new OperatorTC(user);
                        CurrentUser.setCurrentUser(cus);
                        loadScene("/fxml/tc_orders_view.fxml");
                        break;
                    }
                    case 1: {
                        OperatorWC cus = new OperatorWC(user);
                        CurrentUser.setCurrentUser(cus);
                        loadScene("/fxml/wc_orders_view.fxml");
                        break;
                    }
                    case 4: {
                        Customer cus = new Customer(user);
                        CurrentUser.setCurrentUser(cus);
                        loadScene("/fxml/customer_orders_view.fxml");
                        break;
                    }
                }
            } else {
                System.out.println("wtf?");
            }

        }
    }

    private void loadScene(String resource) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setTitle("My orders");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
