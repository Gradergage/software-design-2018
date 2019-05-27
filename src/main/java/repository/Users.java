package repository;

import model.ModelTypes;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static Users instance;
    private List<User> users;

    private Users() {
        users = new ArrayList<>();
        int id = 0;
        User cc = new User();
        cc.setId(id++);
        cc.setName("Митрополит Патриах Cyrillic");
        cc.setType(ModelTypes.TYPE_USER_OPERATOR_CC);
        cc.setLogin("cc");
        cc.setPassword("cc");
        users.add(cc);

        User tc = new User();
        tc.setId(id++);
        tc.setName("Казантип Ибица Отдыхаем");
        tc.setType(ModelTypes.TYPE_USER_OPERATOR_TC);
        tc.setLogin("tc");
        tc.setPassword("tc");
        users.add(tc);

        User tc2 = new User();
        tc2.setId(id++);
        tc2.setName("Ghbdtn Ghbdtn Ghbdtn");
        tc2.setType(ModelTypes.TYPE_USER_OPERATOR_TC);
        tc2.setLogin("tc2");
        tc2.setPassword("tc2");
        users.add(tc2);

        User wc = new User();
        wc.setId(id++);
        wc.setName("Сова Полярная Смешная");
        wc.setType(ModelTypes.TYPE_USER_OPERATOR_WC);
        wc.setLogin("wc");
        wc.setPassword("wc");
        users.add(wc);

        User wc2 = new User();
        wc2.setId(id++);
        wc2.setName("Римский Жребий Брошен");
        wc2.setType(ModelTypes.TYPE_USER_OPERATOR_WC);
        wc2.setLogin("wc2");
        wc2.setPassword("wc2");
        users.add(wc2);

        User cus = new User();
        cus.setId(id++);
        cus.setName("Куй Железо Не-отходя-от-кассы");
        cus.setType(ModelTypes.TYPE_USER_CUSTOMER);
        cus.setLogin("cus");
        cus.setPassword("cus");
        users.add(cus);
        System.out.println("Users list:");
        for(User n : users)
        {
            System.out.println(String.format("%5d %s",n.getId(),n.getLogin()));
        }
    }

    public static synchronized Users getInstance() {
        if (instance == null)
            instance = new Users();
        return instance;
    }

    public User getUserById(long id) {
        for (User n : users) {
            if (n.getId() == id)
                return n;
        }
        return null;
    }


}
