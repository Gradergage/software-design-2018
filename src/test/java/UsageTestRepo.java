import entities.*;
import model.Order;
import org.junit.Before;
import org.junit.Test;

public class UsageTestRepo {

    private Customer customer;
    private OperatorCC operatorCC;
    private OperatorTC tc1;
    private OperatorTC tc2;
    private OperatorWC wc1;
    private OperatorWC wc2;
    private RepoApi repo;

    @Before
    public void initTest() {
        repo = new RepoApi();
        customer = new Customer(repo.getUsers().getUserById(5));
        operatorCC = new OperatorCC(repo.getUsers().getUserById(0));
        tc1 = new OperatorTC(repo.getUsers().getUserById(1));
        tc2 = new OperatorTC(repo.getUsers().getUserById(2));
        wc1 = new OperatorWC(repo.getUsers().getUserById(3));
        wc2 = new OperatorWC(repo.getUsers().getUserById(4));
        //  System.out.println(customer);
    }

    /*    @Test
        public void () {

        }*/
    @Test
    public void orderCreation() {

        Order currentOrder = customer.createNewOrder();
        customer.addOrderPosition(currentOrder, "Политехничекская 29", repo.getTariffs().getTariffById(2));
        customer.addOrderPosition(currentOrder, "Технической Полли 3.14", repo.getTariffs().getTariffById(1));
        customer.showOrders();
        operatorCC.showOrders();
        operatorCC.acceptOrder(currentOrder.getId());
        repo.getOrders().showOrder(currentOrder);
    }

    @Test
    public void addingOperatorsTC() {

        /*
         Adding user TC1:
         Operator->Api->order->inserting user(operator->api->user)
         */
        operatorCC.getOrders()
                .getOrderById(0)
                .getOperatorsTC()
                .add(operatorCC.getUsers().getUserById(1));
        repo.getOrders().showOrder(operatorCC.getOrders()
                .getOrderById(0));
    }

    @Test
    public void addingInformationByTC() {

    }
}