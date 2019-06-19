package gui;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Order;
import model.PaymentDocument;
import users.OperatorCC;

public class PayDocController {
    public TextField workField;
    public TextField deviceField;
    public TextField dataField;
    public Button applyButton;
    private Order order;

    public PayDocController() {
    }

    public void initialize() {
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void apply() {
        OperatorCC user = (OperatorCC) CurrentUser.getUser();
        PaymentDocument pd = new PaymentDocument();
        pd.setData(dataField.getText());
        pd.setWorkCost(Long.valueOf(workField.getText()));
        pd.setDeviceCost(Long.valueOf(deviceField.getText()));
        user.addPaymentDoc(order, pd);
        workField.getScene().getWindow().hide();
    }
}
