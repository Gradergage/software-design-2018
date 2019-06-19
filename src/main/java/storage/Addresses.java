package storage;

import model.Address;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class Addresses {

    private static ArrayList<Address> addresses = new ArrayList<>();

    public static ArrayList<Address> get() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<Address> resTemp = session.createQuery("from Address", Address.class).list();
        session.getTransaction().commit();
        session.close();
        addresses.clear();
        addresses.addAll(resTemp);
        return addresses;
    }

    public static void add(Address address) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(address);
        session.getTransaction().commit();
        session.close();
    }

    public static void remove(Address order) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(order);
        session.getTransaction().commit();
        session.close();
    }

 /*   public Order searchTariff(Order tariff) {
        return addresses.stream().filter(x -> x.getName().equals(tariff.getName()))
                .min(Comparator.comparing(Order::getId))
                .orElseThrow(() -> new NoSuchElementException(tariff.getName() + " not present yet"));
    }*/
}
