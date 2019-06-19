package gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelTypes;
import model.Order;
import storage.Orders;
import users.Customer;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerOrdersController {

    private Customer customer;

    public TableView<Order> tableView;
    public Label userName;

    public Button createOrder;
    public Button logoutButton;
    public TableColumn<Order, Long> idCol;
    public TableColumn<Order, String> addressCol;
    public TableColumn<Order, String> tariffCol;
    public TableColumn<Order, String> statusCol;

    public CustomerOrdersController() {
    }

    public void initialize() {
        customer = (Customer) CurrentUser.getUser();


        userName.setText(customer.getUser().getName());
        tableView.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Order order = row.getItem();
                    Orders.showOrder(order);
                    openOrderEditor(order);
                }

            });
            return row;
        });
        idCol.setCellValueFactory(x -> new SimpleObjectProperty<Long>(x.getValue().getId()));
        addressCol.setCellValueFactory(x -> new SimpleObjectProperty<String>(x.getValue().getAddress().getAddress()));
        tariffCol.setCellValueFactory(x -> new SimpleObjectProperty<String>(x.getValue().getTariff().getName() + " " + x.getValue().getTariff().getPrice()));
        statusCol.setCellValueFactory(x -> new SimpleObjectProperty<String>(ModelTypes.getStringStatus(x.getValue())));

        updateTable();

    }

    private void updateTable() {
        ArrayList<Order> orders = customer.getOrders();
        ObservableList<Order> items = FXCollections.observableArrayList(orders);
        tableView.getItems().removeAll(tableView.getItems());
        tableView.setItems(items);

    }

    public void logout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setTitle("Authorize");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void createOrder() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/order_creation_form.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(tableView.getScene().getWindow());
            stage.setTitle("Create order");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateTable();
    }

    private void openOrderEditor(Order order) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/order_form.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(tableView.getScene().getWindow());
            stage.setTitle("Order");
            stage.setScene(new Scene(root1));

            OrderEditController controller = fxmlLoader.<OrderEditController>getController();
            controller.setUser(customer);
            controller.initData(order);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateTable();
    }
}
