package repository;

import model.*;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Orders {

    private static ArrayList<Order> orders = new ArrayList<>();

    public static ArrayList<Order> get() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<Order> resTemp = session.createQuery("from Order", Order.class).list();
        session.getTransaction().commit();
        session.close();
        orders.addAll(resTemp);
        return orders;
    }
    public static void add(Order order) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }

    public static void update(Order order) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }

    public static void remove(Order order) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(order);
        session.getTransaction().commit();
        session.close();
    }

    public static Order searchById(long id) {
           return get().stream().filter(x -> x.getId()==id)
                   .min(Comparator.comparing(Order::getId))
                   .orElseThrow(() -> new NoSuchElementException(id + " doesn't exist"));
    }
    public static void showOrder(Order n) {

        System.out.println("\nOrder " + n.getId());
        if (n.getCustomer() != null)
            System.out.println("Customer: " + n.getCustomer().getName());
        else
            System.out.println("Customer: null");

        if (n.getOperatorCC() != null)
            System.out.println("OperatorCC: " + n.getOperatorCC().getName());
        else
            System.out.println("OperatorCC: null");
        if (n.getOperatorCC() != null)
            System.out.println("OperatorCC: " + n.getOperatorsTC().getName());
        else
            System.out.println("OperatorCC: null");
        System.out.println("Address: " + n.getAddress().getAddress());
        System.out.println("Tariff: " + n.getTariff().getName() + n.getTariff().getPrice());
        System.out.println("Status: " + n.getStatus());
        System.out.println("PaymentStatus: " + n.getPaymentStatus());
        System.out.println("Data TC: ");
        System.out.println("        " + "\"" + n.getDataTC() + "\"");

            if (n.getWorkOrder() != null) {
                System.out.println("Order WC " + n.getWorkOrder().getId());
                showWorkOrder(n.getWorkOrder());
            }

        System.out.println();
    }

    private static void showWorkOrder(WorkOrder n) {
        System.out.println("    WC operators: " + n.getOperatorWC().getName());
       // System.out.println("        " + n.getOperatorWC().getId() + " " + n.getOperatorWC().getLogin());
        System.out.println("    Status: " + n.getStatus());
        System.out.println("    Data WC:");
            System.out.println("        " + "\"" + n.getDataWC() + "\"");
        if (n.getReportWC() != null) {
            System.out.println("    Report WC:");
            System.out.println("        " + "\"" + n.getReportWC().getData() + "\"");
        }
        else
        {
            System.out.println("    Report WC: null");
        }

    }
}
