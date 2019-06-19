package storage;


import model.WorkOrder;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkOrders {

    private static ArrayList<WorkOrder> workOrders = new ArrayList<>();

    public static ArrayList<WorkOrder> get() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<WorkOrder> resTemp = session.createQuery("from WorkOrder", WorkOrder.class).list();
        session.getTransaction().commit();
        session.close();
        workOrders.clear();
        workOrders.addAll(resTemp);
        return workOrders;
    }

    public static void add(WorkOrder workOrder) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(workOrder);
        session.getTransaction().commit();
        session.close();
    }

    public static void remove(WorkOrder workOrder) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(workOrder);
        session.getTransaction().commit();
        session.close();
    }

/*    public Tariff searchTariff(Tariff tariff) {
        return workOrders.stream().filter(x -> x.getName().equals(tariff.getName()))
                .min(Comparator.comparing(Tariff::getId))
                .orElseThrow(() -> new NoSuchElementException(tariff.getName() + " not present yet"));
    }*/
}
