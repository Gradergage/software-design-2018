package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Address;
import model.Order;
import model.Tariff;
import model.User;
import storage.Tariffs;
import users.Customer;
import users.MarkedUser;
import users.OperatorCC;

import java.util.ArrayList;

public class AssignUserController {
    public ComboBox<User> comboBox;
    public Button assignButton;
    public ArrayList<User> users;
    private OperatorCC op;
    private String type;
    private MarkedUser user;
    private Order order;
    Callback<ListView<User>, ListCell<User>> cellFactory = new Callback<ListView<User>, ListCell<User>>() {
        @Override
        public ListCell<User> call(ListView<User> param) {
            final ListCell<User> cell = new ListCell<User>() {
                @Override
                protected void updateItem(User t, boolean bln) {
                    super.updateItem(t, bln);
                    if (t != null) {
                        setText(t.getName());
                    } else {
                        setText(null);
                    }
                }

            };
            return cell;
        }
    };

    public void setUsers(ArrayList<User> users, String type, Order order) {
        user = CurrentUser.getUser();
        this.type = type;
        this.users = users;
        this.order = order;
        op = (OperatorCC) CurrentUser.getUser();
        ObservableList<User> items = FXCollections.observableArrayList(users);
        comboBox.getItems().addAll(items);

        comboBox.setCellFactory(cellFactory);
        comboBox.setButtonCell(cellFactory.call(null));
        comboBox.setValue(items.get(0));
    }

    public void initialize() {

    }

    public void assignUser() {
        if(type.equals("tc"))
        op.acceptOrder(order, comboBox.getValue());
        if(type.equals("wc"))
        op.createWorkOrder(order, comboBox.getValue());
        comboBox.getScene().getWindow().hide();
    }
}
