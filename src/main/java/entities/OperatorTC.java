package entities;

import model.ModelTypes;
import model.Order;
import model.User;

import java.util.List;

public class OperatorTC extends RepoApi implements UserInterface {
    private User user;

    public OperatorTC(User user) {
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
            for (User u : n.getOperatorsTC()) {
                if (u.getId() == user.getId())
                    System.out.println(String.format("%d, %d addresses, customer: %s, operatorCC: %s",
                            n.getId(),
                            n.getAddresses().size(),
                            n.getCustomer().getName(),
                            n.getOperatorCC().getName()));
            }
        }
        return orders.getOrders();
    }

    @Override
    public void editOrder(long id, String data) {
        Order order = orders.getOrderById(id);
        order.getDataTC().add(data);
    }

    public void editOrder(Order order, String data) {
        order.getDataTC().add(data);
    }

    @Override
    public void closeOrder(long id) {
        Order order = orders.getOrderById(id);
        order.setStatus(ModelTypes.ORDER_STATUS_WAITING);
    }

    public void closeOrder(Order order) {
      //  Order order = orders.getOrderById(id);
        order.setStatus(ModelTypes.ORDER_STATUS_WAITING);
    }
}
