package utils;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                setUp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static void addClasses(MetadataSources metadataSources) {
        metadataSources
                .addAnnotatedClass(PaymentDocument.class)
                .addAnnotatedClass(ReportWC.class)
                .addAnnotatedClass(Tariff.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(WorkOrder.class);
    }

    private static void setUp() throws Exception {

        final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .loadProperties("/hibernate.properties")
                .build();

        try {
            MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
            addClasses(metadataSources);
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            throw new Exception(e);
        }
    }

}