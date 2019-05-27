package entities;

import model.Order;
import model.User;

import java.util.List;

public class OperatorWC extends RepoApi implements UserInterface {
    private User user;

    public OperatorWC(User user) {
        this.user = user;
    }

    @Override
    public boolean login(String login, String password) {
        return false;
    }

    @Override
    public List<Order> showOrders() {
        return null;
    }

    @Override
    public void editOrder(long id) {

    }

    @Override
    public void closeOrder(long id) {

    }
}
