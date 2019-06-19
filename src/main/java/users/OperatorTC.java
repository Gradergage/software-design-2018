package users;

import model.ModelTypes;
import model.Order;
import model.User;
import org.hibernate.Session;
import storage.Orders;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class OperatorTC implements MarkedUser {

    private User user;
    private ArrayList<Order> myOrders = new ArrayList<>();

    public OperatorTC(User user) {
        this.user = user;

    }

    public void addData(Order order, String data) {
        order.setDataTC(data);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    public void closeOrder(Order order) {
        order.setStatus(ModelTypes.ORDER_STATUS_COMPLETED);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    public ArrayList<Order> getOrders() {
        myOrders.clear();
        myOrders.addAll(Orders.get()
                .stream()
                .filter(x -> x.getOperatorsTC().getId() == user.getId() && x.getStatus() != ModelTypes.ORDER_PAYMENT_STATUS_REJECTED)
                .collect(Collectors.toList()));
        return myOrders;
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
