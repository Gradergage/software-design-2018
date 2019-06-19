package storage;

import model.ReportWC;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class ReportsWC {

    private static ArrayList<ReportWC> reportsWc = new ArrayList<>();

    public static ArrayList<ReportWC> get() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<ReportWC> resTemp = session.createQuery("from ReportWC", ReportWC.class).list();
        session.getTransaction().commit();
        session.close();
        reportsWc.clear();
        reportsWc.addAll(resTemp);
        return reportsWc;
    }

    public static void add(ReportWC reportWC) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(reportWC);
        session.getTransaction().commit();
        session.close();
    }

    public static void remove(ReportWC reportWC) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(reportWC);
        session.getTransaction().commit();
        session.close();
    }

 /*   public Order searchTariff(Order tariff) {
        return reportsWc.stream().filter(x -> x.getName().equals(tariff.getName()))
                .min(Comparator.comparing(Order::getId))
                .orElseThrow(() -> new NoSuchElementException(tariff.getName() + " not present yet"));
    }*/
}
