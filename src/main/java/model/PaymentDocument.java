package model;
import javax.persistence.*;

@Entity(name = "PaymentDocument")
@Table(name = "payment_documents")
public class PaymentDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_generator")
    @SequenceGenerator(name="doc_generator", sequenceName = "doc_id_seq", allocationSize = 1)
    @Column(name = "doc_id")
    private long id;

    @Column(name = "work_cost")
    private long workCost;

    @Column(name = "device_cost")
    private long deviceCost;

    @Column(name = "data")
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTotalCost() {
        return workCost+deviceCost;
    }

    public long getWorkCost() {
        return workCost;
    }

    public void setWorkCost(long workCost) {
        this.workCost = workCost;
    }

    public long getDeviceCost() {
        return deviceCost;
    }

    public void setDeviceCost(long deviceCost) {
        this.deviceCost = deviceCost;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
