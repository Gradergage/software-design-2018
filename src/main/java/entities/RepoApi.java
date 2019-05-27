package entities;

import repository.*;

public class RepoApi {
    Devices devices = Devices.getInstance();
    Orders orders = Orders.getInstance();
    PaymentDocuments documents = PaymentDocuments.getInstance();
    ReportsWC reports = ReportsWC.getInstance();
    Tariffs tariffs = Tariffs.getInstance();
    Users users = Users.getInstance();
    WorkOrders workOrders = WorkOrders.getInstance();

    public Devices getDevices() {
        return devices;
    }

    public Orders getOrders() {
        return orders;
    }

    public PaymentDocuments getDocuments() {
        return documents;
    }

    public ReportsWC getReports() {
        return reports;
    }

    public Tariffs getTariffs() {
        return tariffs;
    }

    public Users getUsers() {
        return users;
    }

    public WorkOrders getWorkOrders() {
        return workOrders;
    }
}
