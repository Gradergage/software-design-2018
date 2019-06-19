package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelTypes;
import model.Order;
import model.ReportWC;
import model.User;
import storage.Orders;
import storage.Users;
import users.*;

import javax.jws.WebParam;
import java.io.IOException;
import java.util.ArrayList;

public class OrderEditController {

    MarkedUser user;
    Order order;

    public Label labelID;
    public Label labelCustomer;
    public Label labelCC;
    public Label labelAddress;
    public Label labelStatus;
    public Label labelTariff;
    public Label labelTC;
    public Label labelWC;
    public Label labelDocID;

    public Button addReport;
    public Button closeOrder;
    public Button editOrder;
    public Button rejectOrder;

    public Pane paneTC;
    public Pane paneDoc;
    public Pane paneWC;

    public TextArea textAreaTC;
    public TextArea textAreaDoc;
    public TextArea textAreaWC;

    public OrderEditController() {
    }

    public void initialize() {
        //Customer part
    }


    public void setUser(MarkedUser user) {
        this.user = user;
    }

    public void initData(Order order) {
        this.order = order;
        addReport.setVisible(false);
        rejectOrder.setVisible(false);
        switch (user.getUser().getType()) {
            case 0: {
                initCC();
                break;
            }
            case 1: {
                initWC();
                break;
            }
            case 2: {
                initTC();
                break;
            }
            case 4: {
                initCustomer();
                break;
            }
        }
    }

    private void initCC() {
        Orders.showOrder(order);
        labelID.setText(Long.toString(order.getId()));
        labelCustomer.setText(order.getCustomer().getName());
        labelAddress.setText(order.getAddress().getAddress());
        labelTariff.setText(order.getTariff().getName());
        labelStatus.setText(ModelTypes.getStringStatus(order));
        textAreaDoc.setEditable(false);
        textAreaTC.setEditable(false);
        textAreaWC.setEditable(false);
        //cc
        if (order.getOperatorCC() == null) {
            labelCC.setText("None");
        } else {
            labelCC.setText(order.getOperatorCC().getName());
        }
        //tc
        if (order.getOperatorsTC() == null) {
            paneTC.setVisible(false);
        } else {
            labelTC.setText(order.getOperatorsTC().getName());
            textAreaTC.setText(order.getDataTC());
        }
        //doc
        if (order.getPaymentDocument() == null) {
            paneDoc.setVisible(false);
        } else {
            labelDocID.setText(Long.toString(order.getPaymentDocument().getId()));
            textAreaDoc.setText(order.getPaymentDocument().getResult());
        }
        //wc
        if (order.getWorkOrder() == null) {
            paneWC.setVisible(false);

        } else {
            labelWC.setText(order.getWorkOrder().getOperatorWC().getName());
            textAreaWC.setText(order.getWorkOrder().getDataWC());
        }
    }

    private void initTC() {
        Orders.showOrder(order);
        labelID.setText(Long.toString(order.getId()));
        labelCustomer.setText(order.getCustomer().getName());
        labelAddress.setText(order.getAddress().getAddress());
        labelTariff.setText(order.getTariff().getName());
        labelStatus.setText(ModelTypes.getStringStatus(order));
        labelCC.setText(order.getOperatorCC().getName());
        textAreaTC.setText(order.getDataTC());
        labelTC.setText(order.getOperatorsTC().getName());
      //  labelWC.setText(order.getWorkOrder().getOperatorWC().getName());
        paneDoc.setVisible(false);
        paneWC.setVisible(false);
    }

    private void initWC() {
        Orders.showOrder(order);
        closeOrder.setVisible(false);
        labelID.setText(Long.toString(order.getId()));
        labelCustomer.setText(order.getCustomer().getName());
        labelAddress.setText(order.getAddress().getAddress());
        labelTariff.setText(order.getTariff().getName());
        labelStatus.setText(ModelTypes.getStringStatus(order));
        labelCC.setText(order.getOperatorCC().getName());
        textAreaTC.setText(order.getDataTC());
        textAreaTC.setEditable(false);
        labelTC.setText(order.getOperatorsTC().getName());
        labelWC.setText(order.getWorkOrder().getOperatorWC().getName());
        paneDoc.setVisible(false);
        closeOrder.setVisible(false);
    }
    private void initCustomer() {
        labelID.setText(Long.toString(order.getId()));
        labelCustomer.setText(order.getCustomer().getName());
        labelAddress.setText(order.getAddress().getAddress());
        labelTariff.setText(order.getTariff().getName());
        labelStatus.setText(ModelTypes.getStringStatus(order));
        textAreaDoc.setEditable(false);
        textAreaTC.setEditable(false);
        textAreaWC.setEditable(false);
        editOrder.setVisible(false);
        closeOrder.setVisible(false);
        addReport.setVisible(false);
        if (ModelTypes.isNeedPayment(order)) {
            closeOrder.setVisible(true);
            closeOrder.setText("Pay order");
            rejectOrder.setVisible(true);
        }
        //cc
        if (order.getOperatorCC() == null) {
            labelCC.setText("None");
        } else {
            labelCC.setText(order.getOperatorCC().getName());
        }
        //tc
        if (order.getOperatorsTC() == null) {
            paneTC.setVisible(false);
        } else {
            labelTC.setText(order.getOperatorsTC().getName());
            textAreaTC.setText(order.getDataTC());
        }
        //doc
        if (order.getPaymentDocument() == null) {
            paneDoc.setVisible(false);
        } else {
            labelDocID.setText(Long.toString(order.getPaymentDocument().getId()));
            textAreaDoc.setText(order.getPaymentDocument().getResult());
        }
        //wc
        if (order.getWorkOrder() == null) {
            paneWC.setVisible(false);

        } else {
            labelWC.setText(order.getWorkOrder().getOperatorWC().getName());
            textAreaWC.setText(order.getWorkOrder().getDataWC());
        }

    }

    public void rejectOrder() {
        Customer cus = (Customer) user;
        cus.rejectOrder(order);
        labelID.getScene().getWindow().hide();
    }

    public void addReport() {
    }

    public void closeOrder() {
        switch (user.getUser().getType()) {
            case 0: {
                closeOrderCC();
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 4: {
                payOrder();
                break;
            }
        }
    }

    public void payOrder() {
        Customer cus = (Customer) user;
        cus.payOrder(order);
        labelID.getScene().getWindow().hide();
    }

    public void editOrder() {
        switch (user.getUser().getType()) {
            case 0: {
                editOrderCC();
                break;
            }
            case 1: {
                editOrderWC();
                break;
            }
            case 2: {
                editOrderTC();
                break;
            }
            case 4: {
                payOrder();
                break;
            }
        }
        initData(order);
    }

    public void closeOrderCC() {

        OperatorCC cc = (OperatorCC) user;
        if (order.getWorkOrder() != null)
            if (order.getStatus() == ModelTypes.ORDER_STATUS_COMPLETED
                    &&
                    order.getWorkOrder().getStatus() == ModelTypes.ORDER_PAYMENT_STATUS_WAITING)
                cc.closeOrder(order);
        initData(order);
    }

    public void editOrderCC() {
        OperatorCC cc = (OperatorCC) user;
        if (order.getOperatorCC() == null) {
            openAssignUser("tc");
        } else {
            if (order.getStatus() == ModelTypes.ORDER_STATUS_COMPLETED && order.getPaymentDocument() == null) {
                openPaymentDocumentEditor();
            } else if (order.getStatus() == ModelTypes.ORDER_STATUS_COMPLETED && order.getPaymentStatus() == ModelTypes.ORDER_PAYMENT_STATUS_CONFIRMED) {
                openAssignUser("wc");
            }
        }
        initData(order);
    }

    public void editOrderTC() {
        OperatorTC tc = (OperatorTC) user;
        tc.addData(order, textAreaTC.getText());
        tc.closeOrder(order);
        initData(order);
    }

    public void editOrderWC() {
        OperatorWC cc = (OperatorWC) user;
        cc.addData(order, textAreaWC.getText());
        ReportWC rep=new ReportWC();
        rep.setData(textAreaWC.getText());
        rep.setOrderId(order.getId());
        cc.addReport(order,rep);
        initData(order);
    }

    private void openPaymentDocumentEditor() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/paydoc_creation_form.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(labelDocID.getScene().getWindow());
            stage.setTitle("Paydoc");
            stage.setScene(new Scene(root1));

            PayDocController controller = fxmlLoader.<PayDocController>getController();
            controller.setOrder(order);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openAssignUser(String type) {
        ArrayList<User> users = new ArrayList<>();
        if (type.equals("tc"))
            users = Users.getUsersByRole(ModelTypes.TYPE_USER_OPERATOR_TC);
        if (type.equals("wc"))
            users = Users.getUsersByRole(ModelTypes.TYPE_USER_OPERATOR_WC);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/select_user.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(labelDocID.getScene().getWindow());
            stage.setTitle("Order");
            stage.setScene(new Scene(root1));

            AssignUserController controller = fxmlLoader.<AssignUserController>getController();
            controller.setUsers(users, type, order);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
