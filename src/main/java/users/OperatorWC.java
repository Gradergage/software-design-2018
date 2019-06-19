package users;

import model.*;
import org.hibernate.Session;
import storage.Orders;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class OperatorWC implements MarkedUser{

    private User user;
    private ArrayList<Order> myOrders = new ArrayList<>();
    public OperatorWC(User user) {
        this.user = user;

    }

    public ArrayList<Order> getOrders()
    {
        myOrders.clear();
        myOrders.addAll(Orders.get()
                .stream()
                .filter(x -> {
                    if(x.getWorkOrder()!=null) {
                            return x.getWorkOrder().getOperatorWC().getId() == user.getId();
                    }
                    return false;
                })
                .collect(Collectors.toList()));
        return myOrders;
    }

    public void addData(Order order, String data) {
        WorkOrder wo = order.getWorkOrder();
        wo.setDataWC(data);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(wo);
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    public void addReport(Order order, ReportWC report)
    {
        WorkOrder wo = order.getWorkOrder();
        wo.setReportWC(report);
        wo.setStatus(ModelTypes.ORDER_STATUS_WAITING);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(report);
        session.update(wo);
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
