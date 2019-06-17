package users;

import model.*;
import org.hibernate.Session;
import repository.Orders;
import repository.Users;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class OperatorCC {

    private User user;
    private ArrayList<Order> myOrders = new ArrayList<>();

    public OperatorCC(User user) {
        this.user = user;
    }
    public void getOrders()
    {
        myOrders.addAll(Orders.get());
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
        order.getWorkOrder().setStatus(ModelTypes.ORDER_STATUS_COMPLETED);
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }
}
