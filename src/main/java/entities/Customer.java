package entities;

import model.ModelTypes;
import model.Order;
import model.Tariff;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class Customer extends RepoApi implements UserInterface {

    private User user;

    public Customer(User user) {
        this.user=user;

    }

    @Override
    public boolean login(String login, String password) {
        return false;
    }

    @Override
    public List<Order> showOrders() {
        System.out.println(String.format("%s orders: \n", user.getLogin()));
        for (Order n : orders.getOrders()) {
            if (n.getCustomer().getId() == user.getId())
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

    public Order createNewOrder()
    {
        Order order = new Order();
        order.setCustomer(user);
        order.setStatus(ModelTypes.ORDER_STATUS_IDLE);
        order.setPaymentStatus(ModelTypes.ORDER_PAYMENT_STATUS_IDLE);
        orders.getOrders().add(order);
        return order;
    }

    public Order addOrderPosition(Order order, String address, Tariff tariff)
    {
        List<Tariff> tariffs = order.getTariffs();
        tariffs.add(tariff);

        List<String> addresses = order.getAddresses();
        addresses.add(address);

        return order;
    }

    public Order closeOrder(Order order)
    {
        order.setStatus(ModelTypes.ORDER_STATUS_COMPLETED);
        return order;
    }

    public Order payOrder(Order order)
    {
        if(order.getPaymentDocument()!=null)
        {
            System.out.println(String.format("Bill: \n" +
                    "%10d - Work cost\n"+
                    "%10d - Device cost\n"+
                    "%10d - Total cost",
                    order.getPaymentDocument().getWorkCost(),
                    order.getPaymentDocument().getDeviceCost(),
                    order.getPaymentDocument().getTotalCost()));

            order.setPaymentStatus(ModelTypes.ORDER_PAYMENT_STATUS_CONFIRMED);
        }

        return order;
    }

    public Order rejectOrder(Order order)
    {
        order.setPaymentStatus(ModelTypes.ORDER_PAYMENT_STATUS_REJECTED);
        return order;
    }

}
