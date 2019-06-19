package users;

import model.*;
import org.hibernate.Session;
import storage.Orders;
import storage.Users;
import utils.HibernateUtils;

import java.util.ArrayList;

public class OperatorCC implements MarkedUser{

    private User user;
    private ArrayList<Order> myOrders = new ArrayList<>();

    public OperatorCC(User user) {
        this.user = user;
    }
    public ArrayList<Order> getOrders()
    {
        myOrders.clear();
        myOrders.addAll(Orders.get());
        return myOrders;
    }

    public void acceptOrder(Order order, User tcOp)
    {
        order.setOperatorCC(user);
        order.setOperatorsTC(tcOp);
        order.setStatus(ModelTypes.ORDER_STATUS_ACTIVE);

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    public void addPaymentDoc(Order order, PaymentDocument pd)
    {
        order.setPaymentDocument(pd);
        order.setPaymentStatus(ModelTypes.ORDER_PAYMENT_STATUS_WAITING);

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(pd);
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    public void createWorkOrder(Order order, User wcOp)
    {
        WorkOrder wo=new WorkOrder();
        wo.setOperatorWC(wcOp);
        wo.setStatus(ModelTypes.ORDER_STATUS_ACTIVE);
        order.setWorkOrder(wo);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(wo);
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    public void closeOrder(Order order)
    {
        WorkOrder wo = order.getWorkOrder();
        wo.setStatus(ModelTypes.ORDER_STATUS_COMPLETED);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(wo);
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUser() {
        return user;
    }
}
