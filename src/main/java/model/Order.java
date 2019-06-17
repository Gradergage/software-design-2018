package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Order")
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @Column(name = "order_id")
    private long id;

    @Column(name = "status")
    private int status = ModelTypes.ORDER_STATUS_IDLE;

    @Column(name = "payment_status")
    private int paymentStatus= ModelTypes.ORDER_PAYMENT_STATUS_IDLE;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @OneToOne
    @JoinColumn(name = "operator_cc_id")
    private User operatorCC;

    @OneToOne
    @JoinColumn(name = "operator_tc_id")
    private User operatorsTC;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @Column(name = "data_tc")
    private String dataTC = "";

    /*@OneToMany
    @JoinColumn(name = "devices_id")
    private List<Device> devices = new ArrayList<>();*/

    @OneToOne
    @JoinColumn(name = "doc_id")
    private PaymentDocument paymentDocument;

    @OneToOne
    @JoinColumn(name = "work_order_id")
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

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public User getOperatorsTC() {
        return operatorsTC;
    }

    public void setOperatorsTC(User operatorsTC) {
        this.operatorsTC = operatorsTC;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public String getDataTC() {
        return dataTC;
    }

    public void setDataTC(String dataTC) {
        this.dataTC = dataTC;
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


}
