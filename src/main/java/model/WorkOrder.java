package model;

import javax.jws.WebParam;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "WorkOrder")
@Table(name = "work_orders")
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_orders_generator")
    @SequenceGenerator(name="work_orders_generator", sequenceName = "work_orders_id_seq", allocationSize = 1)
    @Column(name = "work_order_id")
    private long id;

    @Column(name = "status")
    private int status = ModelTypes.ORDER_STATUS_IDLE;

    @Column(name = "data_wc")
    private String dataWC = "";

    @ManyToOne
    @JoinColumn(name="operator_wc_id")
    private User operatorWC;

    @ManyToOne
    @JoinColumn(name="report_id")
    private ReportWC reportWC;

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

    public String getDataWC() {
        return dataWC;
    }

    public void setDataWC(String dataWC) {
        this.dataWC = dataWC;
    }

    public ReportWC getReportWC() {
        return reportWC;
    }

    public void setReportWC(ReportWC reportWC) {
        this.reportWC = reportWC;
    }

    public User getOperatorWC() {
        return operatorWC;
    }

    public void setOperatorWC(User operatorWC) {
        this.operatorWC = operatorWC;
    }
}
