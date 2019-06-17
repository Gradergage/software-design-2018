import mappers.AddressMapper;
import model.*;
import users.*;
import org.hibernate.Session;
import org.junit.Test;
import repository.*;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTests {
    @Test
    public void initTest() throws SQLException {
        DBUtils dbUtils = new DBUtils();
        dbUtils.init();
        AddressMapper mapper = new AddressMapper(dbUtils.getConnection());
        System.out.println(mapper.find("Пархоменко 32").getAddress());

    }

    @Test
    public void hibernateTest() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        try {
            Tariff test = session.get(Tariff.class, 1);
            System.out.println(test.getName());
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    @Test
    public void hibernateTest2() {
       /* Tariffs.add(new  Tariff("халява 1",228));
        Tariffs.add(new  Tariff("халява 2",2280));
        Tariffs.add(new  Tariff("халява 3",1228));
        Tariffs.add(new  Tariff("халява 4",22318));

        User user = new User();
        user.setLogin("cc");
        user.setPassword("123");
        user.setName("Светлана Здравствуйте");
        user.setType(1);
        Users.add(user);

        user = new User();
        user.setLogin("cus1");
        user.setPassword("123");
        user.setName("Жмышенко Валерий Альбертович");
        user.setType(4);
        Users.add(user);

        user = new User();
        user.setLogin("wc1");
        user.setPassword("123");
        user.setName("hello Moto");
        user.setType(1);
        Users.add(user);

        user = new User();
        user.setLogin("wc2");
        user.setPassword("123");
        user.setName("Святой Князь Киевский");
        user.setType(1);
        Users.add(user);

        user = new User();
        user.setLogin("tc1");
        user.setPassword("123");
        user.setName("Пять Морковок");
        user.setType(2);
        Users.add(user);

        user = new User();
        user.setLogin("tc2");
        user.setPassword("123");
        user.setName("Великолепный Кек");
        user.setType(2);
        Users.add(user);
*/
        Customer customer = new Customer(Users.getByLogin("cus1"));
        OperatorCC cc = new OperatorCC(Users.getByLogin("cc"));
        OperatorTC tc = new OperatorTC(Users.getByLogin("tc1"));
        OperatorWC wc = new OperatorWC(Users.getByLogin("wc1"));

        assert (customer.getUser() != null);
        Address address = new Address();
        address.setAddress("Пархоменко 32");

        System.out.println("==Заказчик добавляет заявку и указывает адреса с тарифами==");
        long id= customer.createOrder(address, Tariffs.get().get(1));


        System.out.println("==Заявка заказчика==");
        Orders.showOrder(Orders.searchById(id));


        System.out.println("==КЦ оператор добавляет оператора ТО==");
        cc.acceptOrder(Orders.searchById(id), Users.getByLogin("tc1"));
        assert (Orders.searchById(id).getStatus() == ModelTypes.ORDER_STATUS_ACTIVE);

        System.out.println("==ТО оператор добавляет свою информацию и закрывает заявку==");

        tc.addData(Orders.searchById(id),"Интересно прям очень");
        tc.closeOrder(Orders.searchById(id));
        assert (Orders.searchById(id).getStatus() == ModelTypes.ORDER_STATUS_COMPLETED);

        System.out.println("==КЦ оператор принимает работу ТО и составляет документ об оплате==");

        PaymentDocument pd = new PaymentDocument();
        pd.setData("Ну интересно прям очень, да");
        pd.setDeviceCost(1331);
        pd.setWorkCost(4141);

        cc.addPaymentDoc(Orders.searchById(id),pd);
        assert (Orders.searchById(id).getStatus() == ModelTypes.ORDER_STATUS_COMPLETED);
        assert (Orders.searchById(id).getPaymentStatus() == ModelTypes.ORDER_PAYMENT_STATUS_WAITING);

        System.out.println("==Заказчик оплачивает заявку==");

        customer.payOrder(Orders.searchById(id));
        assert (Orders.searchById(id).getPaymentStatus() == ModelTypes.ORDER_PAYMENT_STATUS_CONFIRMED);

        Orders.showOrder(Orders.searchById(id));

        System.out.println("==КЦ оператор оформляет заявку на работы==");

        cc.createWorkOrder(Orders.searchById(id),Users.getByLogin("wc1"));

        System.out.println("==МО оператор заполняет информацию в заявке ==");

        wc.addData(Orders.searchById(id),"Какая-то полезная информация");

        ReportWC report = new ReportWC();
        report.setData("Отчет такой-то Составлен тем-то Здравствуйте");
        report.setOrderId(Orders.searchById(id).getId());

        wc.addReport(Orders.searchById(id),report);
        Orders.showOrder(Orders.searchById(id));
        System.out.println("==КЦ оператор закрывает заявку ==");

        cc.closeOrder(Orders.searchById(id));
        assert (Orders.searchById(id).getWorkOrder().getStatus() == ModelTypes.ORDER_STATUS_COMPLETED);
        assert (Orders.searchById(id).getStatus() == ModelTypes.ORDER_STATUS_COMPLETED);
        assert (Orders.searchById(id).getPaymentStatus() == ModelTypes.ORDER_PAYMENT_STATUS_CONFIRMED);


        Orders.showOrder(Orders.searchById(id));
    }
}
