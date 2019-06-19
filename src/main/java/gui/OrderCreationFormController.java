package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.Address;
import model.Tariff;
import storage.Tariffs;
import users.Customer;

import java.util.ArrayList;

public class OrderCreationFormController {
    public ComboBox<Tariff> tariffComboBox;
    public Button createButton;
    public TextField addressField;
    private Customer customer;

    Callback<ListView<Tariff>, ListCell<Tariff>> cellFactory = new Callback<ListView<Tariff>, ListCell<Tariff>>() {
        @Override
        public ListCell<Tariff> call(ListView<Tariff> param) {
            final ListCell<Tariff> cell = new ListCell<Tariff>() {
                @Override
                protected void updateItem(Tariff t, boolean bln) {
                    super.updateItem(t, bln);
                    if (t != null) {
                        setText(t.getName() + t.getPrice());
                    } else {
                        setText(null);
                    }
                }

            };
            return cell;
        }
    };

    public void initialize() {
        customer = (Customer) CurrentUser.getUser();
        ArrayList<Tariff> tariffs = Tariffs.get();
        ObservableList<Tariff> items = FXCollections.observableArrayList(tariffs);
        tariffComboBox.getItems().addAll(items);

        tariffComboBox.setCellFactory(cellFactory);
        tariffComboBox.setButtonCell(cellFactory.call(null));
        tariffComboBox.setValue(items.get(0));
    }

    public void createOrder() {
        if (!addressField.getText().equals("")) {
            Address address = new Address();
            address.setAddress(addressField.getText());
            customer.createOrder(address, tariffComboBox.getValue());
        }

        addressField.getScene().getWindow().hide();
    }
}
