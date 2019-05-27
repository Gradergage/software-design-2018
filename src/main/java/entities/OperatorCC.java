package entities;

import model.ModelTypes;
import model.Order;
import model.User;

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
    public void editOrder(long id) {

    }

    @Override
    public void closeOrder(long id) {

    }

    public void acceptOrder(long id)
    {
        Order order = orders.getOrderById(id);
        order.setStatus(ModelTypes.ORDER_STATUS_ACTIVE);
        order.setOperatorCC(user);

    }
}
