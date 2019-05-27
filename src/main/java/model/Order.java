package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private long id;
    private int status;
    private int paymentStatus;
    private User customer;
    private User operatorCC;
    private List<User> operatorsTC=new ArrayList<>();
    private List<String> addresses=new ArrayList<>();
    private List<Tariff> tariffs=new ArrayList<>();
    private List<String> dataTC=new ArrayList<>();
    private List<Device> devices=new ArrayList<>();

    private PaymentDocument paymentDocument;
    private WorkOrder workOrder;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getOperatorCC() {
        return operatorCC;
    }

    public void setOperatorCC(User operatorCC) {
        this.operatorCC = operatorCC;
    }

    public List<User> getOperatorsTC() {
        return operatorsTC;
    }

    public void setOperatorsTC(List<User> operatorsTC) {
        this.operatorsTC = operatorsTC;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getDataTC() {
        return dataTC;
    }

    public void setDataTC(List<String> dataTC) {
        this.dataTC = dataTC;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public PaymentDocument getPaymentDocument() {
        return paymentDocument;
    }

    public void setPaymentDocument(PaymentDocument paymentDocument) {
        this.paymentDocument = paymentDocument;
    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
