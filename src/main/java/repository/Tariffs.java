package repository;

import model.Tariff;

import java.util.ArrayList;
import java.util.List;

public class Tariffs {

    private static Tariffs instance;
    private List<Tariff> tariffs;

    private Tariffs() {
        tariffs = new ArrayList<>();

        int id=0;
        Tariff t1= new Tariff();
        t1.setId(id++);
        t1.setName("Халява 1");
        t1.setPrice(228);

        Tariff t2= new Tariff();
        t2.setId(id++);
        t2.setName("Ультимативная Халява 2000");
        t2.setPrice(13);

        Tariff t3= new Tariff();
        t3.setId(id++);
        t3.setName("Вообще не халява");
        t3.setPrice(124124);

        Tariff t4= new Tariff();
        t4.setId(id++);
        t4.setName("Для не-мамонтов");
        t4.setPrice(999999);

        tariffs.add(t1);
        tariffs.add(t2);
        tariffs.add(t3);
        tariffs.add(t4);
    }

    public static synchronized Tariffs getInstance() {
        if (instance == null)
            instance = new Tariffs();
        return instance;
    }

    public Tariff getTariffById(long id) {
        for (Tariff n : tariffs) {
            if (n.getId() == id)
                return n;
        }
        return null;
    }

}
