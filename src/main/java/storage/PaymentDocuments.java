package storage;

import model.PaymentDocument;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class PaymentDocuments {

    private static ArrayList<PaymentDocument> paymentDocuments = new ArrayList<>();

    public static ArrayList<PaymentDocument> get() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<PaymentDocument> resTemp = session.createQuery("from PaymentDocument", PaymentDocument.class).list();
        session.getTransaction().commit();
        session.close();
        paymentDocuments.clear();
        paymentDocuments.addAll(resTemp);
        return paymentDocuments;
    }

    public static void add(PaymentDocument paymentDocument) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(paymentDocument);
        session.getTransaction().commit();
        session.close();
    }

    public static void remove(PaymentDocument order) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(order);
        session.getTransaction().commit();
        session.close();
    }

 /*   public Order searchTariff(Order tariff) {
        return paymentDocuments.stream().filter(x -> x.getName().equals(tariff.getName()))
                .min(Comparator.comparing(Order::getId))
                .orElseThrow(() -> new NoSuchElementException(tariff.getName() + " not present yet"));
    }*/
}
