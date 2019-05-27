package entities;

import model.ModelTypes;
import model.Order;
import model.ReportWC;
import model.User;

import java.util.List;

public class OperatorWC extends RepoApi implements UserInterface {
    private User user;

    public OperatorWC(User user) {
        this.user = user;
    }

    @Override
    public boolean login(String login, String password) {
        return false;
    }

    @Override
    public List<Order> showOrders() {
        return null;
    }

    @Override
    public void editOrder(long id,String data) {
        orders.getOrderById(id).getWorkOrder().getDataWC().add(data);
    }

    public void addReport(long id,String data)
    {
        ReportWC report = new ReportWC();
        report.setData(data);
        report.setId(id);

        orders.getOrderById(id).getWorkOrder().setReportWC(report);
    }

    @Override
    public void closeOrder(long id) {
        orders.getOrderById(id).getWorkOrder().setStatus(ModelTypes.ORDER_STATUS_COMPLETED);
    }
}
