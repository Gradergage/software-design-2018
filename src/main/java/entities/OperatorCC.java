package entities;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.*;

import java.util.List;

public class OperatorCC extends RepoApi implements UserInterface {
    private User user;

    public OperatorCC(User user) {
        this.user = user;
    }


    @Override
    public boolean login(String login, String password) {
        return false;
    }

    @Override
    public List<Order> showOrders() {

        System.out.println(String.format("%s orders: \n", user.getLogin()));
        for (Order n : orders.getOrders()) {
          //  if (n.getStatus() == ModelTypes.ORDER_PAYMENT_STATUS_IDLE)
                System.out.println(String.format("%d, %d addresses, customer: %s\n",
                        n.getId(),
                        n.getAddresses().size(),
                        n.getCustomer().getName()));
        }
        return orders.getOrders();
    }

    @Override
    public void editOrder(long id, String data) {

    }

    @Override
    public void closeOrder(long id) {
        orders.getOrderById(0).setStatus(ModelTypes.ORDER_STATUS_COMPLETED);
    }

    public void acceptOrder(long id)
    {
        Order order = orders.getOrderById(id);
        order.setStatus(ModelTypes.ORDER_STATUS_ACTIVE);
        order.setOperatorCC(user);

    }

    public void processOrder(long id)
    {
        Order order = orders.getOrderById(id);
        WorkOrder workOrder = new WorkOrder();
        workOrder.setStatus(ModelTypes.ORDER_STATUS_ACTIVE);
        workOrders.addOrder(workOrder);
        order.setWorkOrder(workOrder);
    }
    public void addPaymentDocument(long id)
    {
        Order order = orders.getOrderById(id);
        PaymentDocument doc = new PaymentDocument();
        doc.setDeviceCost(4533);
        doc.setWorkCost(123121);
        doc.setTotalCost(doc.getDeviceCost()+doc.getWorkCost());
        order.setPaymentDocument(doc);
        order.setPaymentStatus(ModelTypes.ORDER_PAYMENT_STATUS_WAITING);
    }
}
