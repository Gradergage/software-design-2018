package repository;

import model.WorkOrder;

import java.util.ArrayList;
import java.util.List;

public class WorkOrders {
    private static WorkOrders instance;
    private List<WorkOrder> orders;

    private WorkOrders() {
        orders = new ArrayList<>();
    }

    public static synchronized WorkOrders getInstance() {
        if (instance == null)
            instance = new WorkOrders();
        return instance;
    }

    public WorkOrder getOrderById(long id) {
        for (WorkOrder n : orders) {
            if (n.getId() == id)
                return n;
        }
        return null;
    }

    public boolean addOrder(WorkOrder order) {
        if (order != null) {
            order.setId(orders.size());
            orders.add(order);
            return true;
        } else
            return false;
    }

    public boolean updateOrder(long id, WorkOrder newOrder) {
        WorkOrder current = getOrderById(id);
        if (current != null) {
            current = newOrder;
            current.setId(id);
            return true;
        } else return false;

    }

    private boolean seekOrder(long id) {
        return getOrderById(id) != null;
    }
}
