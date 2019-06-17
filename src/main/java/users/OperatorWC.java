package users;

import model.ModelTypes;
import model.Order;
import model.ReportWC;
import model.User;
import org.hibernate.Session;
import repository.Orders;
import repository.ReportsWC;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class OperatorWC {

    private User user;
    private ArrayList<Order> myOrders = new ArrayList<>();
    public OperatorWC(User user) {
        this.user = user;

    }

    public void getOrders()
    {
        myOrders.addAll(Orders.get()
                .stream()
                .filter(x -> x.getWorkOrder().getOperatorWC().getId()==user.getId())
                .collect(Collectors.toList()));
    }

    public void addData(Order order, String data) {
        order.getWorkOrder().setDataWC(data);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    public void addReport(Order order, ReportWC report)
    {
        order.getWorkOrder().setReportWC(report);
        order.getWorkOrder().setStatus(ModelTypes.ORDER_STATUS_WAITING);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(report);
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Order> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(ArrayList<Order> myOrders) {
        this.myOrders = myOrders;
    }
}
