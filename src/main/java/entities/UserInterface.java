package entities;

import model.Order;
import repository.Orders;

import java.util.List;

public interface UserInterface {
    boolean login (String login,String password);
    List<Order> showOrders();
    void editOrder(long id, String data);
    void closeOrder(long id);
}
