import entities.*;
import model.ModelTypes;
import model.Order;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsageTestRepo {
    private RepoApi repo = new RepoApi();
    private Customer customer = new Customer(repo.getUsers().getUserById(5));
    private OperatorCC operatorCC = new OperatorCC(repo.getUsers().getUserById(0));
    private OperatorTC tc1 = new OperatorTC(repo.getUsers().getUserById(1));
    private OperatorTC tc2 = new OperatorTC(repo.getUsers().getUserById(2));
    private OperatorWC wc1 = new OperatorWC(repo.getUsers().getUserById(3));
    private OperatorWC wc2 = new OperatorWC(repo.getUsers().getUserById(4));
    @Before
    public void initTest() {
        repo = new RepoApi();
        //  System.out.println(customer);
    }

    /*    @Test
        public void () {

        }*/
    @Test
    public void processAccepting() {

        System.out.println("==Заказчик добавляет заявку и указывает адреса с тарифами==");
        Order currentOrder = customer.createNewOrder();
        customer.addOrderPosition(currentOrder, "Политехничекская 29", repo.getTariffs().getTariffById(2));
        customer.addOrderPosition(currentOrder, "Технической Полли 3.14", repo.getTariffs().getTariffById(1));

        assert (customer.showOrders().size()!=0);

        System.out.println("==КЦ оператор принимает заявку==");
        operatorCC.showOrders();
        operatorCC.acceptOrder(currentOrder.getId());
        repo.getOrders().showOrder(currentOrder);
        assert (repo.getOrders().getOrders().size()!=0);


        System.out.println("==КЦ оператор добавляет оператора ТО==");
        operatorCC.getOrders()
                .getOrderById(0)
                .getOperatorsTC()
                .add(operatorCC.getUsers().getUserById(1));
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));
        assert (repo.getOrders().getOrderById(0).getOperatorsTC().size()!=0);

        System.out.println("==ТО оператор добавляет свою информацию и закрывает заявку==");
        tc1.editOrder(0,"Очередной бред сумасшедшего");
        tc1.editOrder(0,"Невероятно ВНяТнаЯ мысЛь ВеКа");
        tc1.closeOrder(0);
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));

        System.out.println("==КЦ оператор принимает работу ТО и составляет документ об оплате==");
        operatorCC.addPaymentDocument(0);
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));
        assert(repo.getOrders().getOrderById(0).getPaymentStatus()== ModelTypes.ORDER_PAYMENT_STATUS_WAITING);
        assert(repo.getOrders().getOrderById(0).getPaymentStatus()== ModelTypes.ORDER_STATUS_WAITING);

        System.out.println("==Заказчик оплачивает заявку==");
        customer.payOrder(0);
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));
        assert(repo.getOrders().getOrderById(0).getPaymentStatus()== ModelTypes.ORDER_PAYMENT_STATUS_CONFIRMED);
        assert(repo.getOrders().getOrderById(0).getPaymentStatus()== ModelTypes.ORDER_STATUS_ACTIVE);

        System.out.println("==КЦ оператор оформляет заявку на работы==");
        operatorCC.processOrder(0);
        operatorCC.getOrders().getOrderById(0).getWorkOrder().getOperatorsWC().add(repo.getUsers().getUserById(3));
        operatorCC.getOrders().getOrderById(0).getWorkOrder().getOperatorsWC().add(repo.getUsers().getUserById(4));
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));


        System.out.println("==МО оператор заполняет информацию в заявке==");
        wc2.editOrder(0,"очередная идея по захвату мира");
        wc2.editOrder(0,"Регулийский Регулировщик");
        wc2.editOrder(0,"Карл украл у Карла КАААААРЛ");

        wc1.addReport(0,"Отчет такой-то, рубль деньгами - остальное мелочью");
        wc1.closeOrder(0);
        assert (repo.getOrders().getOrderById(0).getWorkOrder().getStatus()==ModelTypes.ORDER_STATUS_COMPLETED);

        System.out.println("==КЦ оператор закрывает заявку");

        operatorCC.closeOrder(0);
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));

        assert (repo.getOrders().getOrderById(0).getStatus()==ModelTypes.ORDER_STATUS_COMPLETED);
        assert (repo.getOrders().getOrderById(0).getPaymentStatus()==ModelTypes.ORDER_PAYMENT_STATUS_CONFIRMED);

    }


    @Test
    public void processRejecting() {

        System.out.println("==Заказчик добавляет заявку и указывает адреса с тарифами==");
        Order currentOrder = customer.createNewOrder();
        customer.addOrderPosition(currentOrder, "Политехничекская 29", repo.getTariffs().getTariffById(2));
        customer.addOrderPosition(currentOrder, "Технической Полли 3.14", repo.getTariffs().getTariffById(1));

        assert (customer.showOrders().size()!=0);

        System.out.println("==КЦ оператор принимает заявку==");
        operatorCC.showOrders();
        operatorCC.acceptOrder(currentOrder.getId());
        repo.getOrders().showOrder(currentOrder);
        assert (repo.getOrders().getOrders().size()!=0);


        System.out.println("==КЦ оператор добавляет оператора ТО==");
        operatorCC.getOrders()
                .getOrderById(0)
                .getOperatorsTC()
                .add(operatorCC.getUsers().getUserById(1));
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));
        assert (repo.getOrders().getOrderById(0).getOperatorsTC().size()!=0);

        System.out.println("==ТО оператор добавляет свою информацию и закрывает заявку==");
        tc1.editOrder(0,"Очередной бред сумасшедшего");
        tc1.editOrder(0,"Невероятно ВНяТнаЯ мысЛь ВеКа");
        tc1.closeOrder(0);
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));

        System.out.println("==КЦ оператор принимает работу ТО и составляет документ об оплате==");
        operatorCC.addPaymentDocument(0);
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));
        assert(repo.getOrders().getOrderById(0).getPaymentStatus()== ModelTypes.ORDER_PAYMENT_STATUS_WAITING);
        assert(repo.getOrders().getOrderById(0).getPaymentStatus()== ModelTypes.ORDER_STATUS_WAITING);

        System.out.println("==Заказчик оплачивает заявку==");
        customer.rejectOrder(0);
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));
        assert(repo.getOrders().getOrderById(0).getPaymentStatus()== ModelTypes.ORDER_PAYMENT_STATUS_REJECTED);
        assert(repo.getOrders().getOrderById(0).getStatus()== ModelTypes.ORDER_STATUS_WAITING);

        System.out.println("==КЦ оператор закрывает заявку");

        operatorCC.closeOrder(0);
        repo.getOrders().showOrder(repo.getOrders().getOrderById(0));

        assert (repo.getOrders().getOrderById(0).getStatus()==ModelTypes.ORDER_STATUS_COMPLETED);
        assert (repo.getOrders().getOrderById(0).getPaymentStatus()==ModelTypes.ORDER_PAYMENT_STATUS_REJECTED);

    }
}