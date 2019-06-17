package repository;

import model.User;
import org.hibernate.Session;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> get() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> resTemp = session.createQuery("from User", User.class).list();
        session.getTransaction().commit();
        session.close();
        users.addAll(resTemp);
        return users;
    }

    public static User getByLogin(String login) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        User resTemp = session.createQuery("from User as user where user.login like '"+login+"'", User.class).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return resTemp;
    }

    public static ArrayList<User> getByType(int type) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> resTemp = session.createQuery("from User as user where user.type like '"+type+"'", User.class).list();
        session.getTransaction().commit();
        session.close();
        ArrayList<User> res = new ArrayList<>();
        res.addAll(resTemp);
        return res;
    }

    public static void add(User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public static void remove(User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
        session.close();
    }

/*    public Tariff searchTariff(Tariff tariff) {
        return users.stream().filter(x -> x.getName().equals(tariff.getName()))
                .min(Comparator.comparing(Tariff::getId))
                .orElseThrow(() -> new NoSuchElementException(tariff.getName() + " not present yet"));
    }*/
}
