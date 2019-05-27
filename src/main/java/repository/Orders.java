package repository;

import entities.Customer;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private static Orders instance;
    private List<Order> orders;

    private Orders() {
        orders = new ArrayList<>();
    }

    public static synchronized Orders getInstance() {
        if (instance == null)
            instance = new Orders();
        return instance;
    }

    public Order getOrderById(long id) {
        for (Order n : orders) {
            if (n.getId() == id)
                return n;
        }
        return null;
    }

    public boolean addOrder(Order order) {
        if (order != null) {
            order.setId(orders.size());
            orders.add(order);
            return true;
        } else
            return false;
    }

    public boolean updateOrder(long id, Order newOrder) {
        Order current = getOrderById(id);
        if (current != null) {
            current = newOrder;
            current.setId(id);
            return true;
        } else return false;

    }

    private boolean seekOrder(long id) {
        return getOrderById(id) != null;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void show() {
        System.out.println("Devices list:");
        for (Order n : orders) {
            showOrder(n);
        }
    }

    public void showOrder(Order n) {

        System.out.println("id " + n.getId());
        if (n.getCustomer() != null)
            System.out.println("Customer: " + n.getCustomer().getName());
        else
            System.out.println("Customer: null");

        if (n.getOperatorCC() != null)
            System.out.println("OperatorCC: " + n.getOperatorCC().getName());
        else
            System.out.println("OperatorCC: null");
        System.out.println("TC operators: " + n.getOperatorsTC().size());
        System.out.println("Addresses: " + n.getAddresses().size());
        System.out.println("Status: " + n.getStatus());
        System.out.println("PaymentStatus: " + n.getPaymentStatus());
    }
}
