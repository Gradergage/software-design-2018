package repository;

import entities.Customer;
import model.Order;
import model.User;
import model.WorkOrder;

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

        System.out.println("\nOrder " + n.getId());
        if (n.getCustomer() != null)
            System.out.println("Customer: " + n.getCustomer().getName());
        else
            System.out.println("Customer: null");

        if (n.getOperatorCC() != null)
            System.out.println("OperatorCC: " + n.getOperatorCC().getName());
        else
            System.out.println("OperatorCC: null");
        System.out.println("TC operators: " + n.getOperatorsTC().size());
        for (User u : n.getOperatorsTC())
            System.out.println("    " + u.getId() + " " + u.getLogin());
        System.out.println("Addresses: " + n.getAddresses().size());
        for (int i = 0; i < n.getAddresses().size(); i++) {
            System.out.println("    " + n.getAddresses().get(i) + " -> " +
                    "\"" + n.getTariffs().get(i).getName() + "\"" + " " +
                    n.getTariffs().get(i).getPrice());
        }
        System.out.println("Status: " + n.getStatus());
        System.out.println("PaymentStatus: " + n.getPaymentStatus());

        System.out.println("Data TC:");
        for (String s : n.getDataTC())
            System.out.println("    " + "\"" + s + "\"");

        if (n.getPaymentDocument() != null) {
            System.out.println("Payment document:");
            System.out.println("    Work cost:" + n.getPaymentDocument().getWorkCost());
            System.out.println("    Device cost:" + n.getPaymentDocument().getDeviceCost());
            System.out.println("    Total cost:" + n.getPaymentDocument().getTotalCost());
        }

        if (n.getWorkOrder() != null) {
            System.out.println("Order WC " + n.getWorkOrder().getId());
            showWorkOrder(n.getWorkOrder());
        }

        System.out.println();
    }

    private void showWorkOrder(WorkOrder n) {
        System.out.println("    WC operators: " + n.getOperatorsWC().size());
        for (User u : n.getOperatorsWC())
            System.out.println("        " + u.getId() + " " + u.getLogin());

        System.out.println("    Data WC:");
        for (String s : n.getDataWC())
            System.out.println("        " + "\"" + s + "\"");
        if (n.getReportWC() != null) {
            System.out.println("    Report WC:");
            System.out.println("        " + n.getReportWC().getData());
        }

    }
}
