package model;

import javax.persistence.*;

@Entity(name = "ReportWC")
@Table(name = "reports")
public class ReportWC {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reports_generator")
    @SequenceGenerator(name="reports_generator", sequenceName = "reports_id_seq", allocationSize = 1)
    @Column(name = "report_id")
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "data")
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
