package users;

import model.*;
import org.hibernate.Session;
import repository.Orders;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Customer {

    private User user;
    private ArrayList<Order> myOrders = new ArrayList<>();

    public Customer(User user) {
        this.user = user;

    }

    public void getOrders() {
        myOrders.addAll(Orders.get()
                .stream()
                .filter(x -> x.getCustomer().getId() == user.getId())
                .collect(Collectors.toList()));

    }

    public long createOrder(Address address, Tariff tariff) {
        Order order = new Order();
        order.setCustomer(user);
        order.setAddress(address);
        order.setTariff(tariff);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(address);
        session.save(order);
        session.getTransaction().commit();
        session.close();

        return order.getId();
    }

    public void payOrder(Order order) {
        order.setPaymentStatus(ModelTypes.ORDER_PAYMENT_STATUS_CONFIRMED);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    public void rejectOrder(Order order) {
        order.setPaymentStatus(ModelTypes.ORDER_PAYMENT_STATUS_REJECTED);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
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
}
