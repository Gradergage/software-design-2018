import storage.TariffsMapper;
import model.*;
import users.*;
import org.hibernate.Session;
import org.junit.Test;
import storage.*;
import utils.*;

import java.sql.SQLException;

public class DBTests {
    @Test
    public void initTest() throws SQLException {
        DBUtils dbUtils = new DBUtils();
        dbUtils.init();
        TariffsMapper mapper = new TariffsMapper(dbUtils.getConnection());
        System.out.println(mapper.getTariffsJson());
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
    public void casePaid() {
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
        cc.acceptOrder(Orders.searchById(id), Users.getByLogin("tc2"));
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

        cc.createWorkOrder(Orders.searchById(id),Users.getByLogin("wc2"));

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
    @Test
    public void caseRefuse() {

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
        cc.acceptOrder(Orders.searchById(id), Users.getByLogin("tc2"));
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

        customer.rejectOrder(Orders.searchById(id));
        assert (Orders.searchById(id).getPaymentStatus() == ModelTypes.ORDER_PAYMENT_STATUS_REJECTED);

        Orders.showOrder(Orders.searchById(id));

        System.out.println("==КЦ оператор оформляет заявку на работы==");

        cc.createWorkOrder(Orders.searchById(id),Users.getByLogin("wc2"));

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
