package storage;


import model.Tariff;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class Tariffs {

    private static ArrayList<Tariff> tariffs = new ArrayList<>();

    public static ArrayList<Tariff> get() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<Tariff> resTemp = session.createQuery("from Tariff", Tariff.class).list();
        session.getTransaction().commit();
        session.close();
        tariffs.clear();
        tariffs.addAll(resTemp);
        return tariffs;
    }

    public static void add(Tariff tariff) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(tariff);
        session.getTransaction().commit();
        session.close();
    }

    public static void remove(Tariff tariff) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(tariff);
        session.getTransaction().commit();
        session.close();
    }

/*    public Tariff searchTariff(Tariff tariff) {
        return tariffs.stream().filter(x -> x.getName().equals(tariff.getName()))
                .min(Comparator.comparing(Tariff::getId))
                .orElseThrow(() -> new NoSuchElementException(tariff.getName() + " not present yet"));
    }*/
}
