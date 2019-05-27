package model;

import java.util.List;

public class WorkOrder {
    private long id;
    private int status;
    private List<String> dataWC;
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

    public List<String> getDataWC() {
        return dataWC;
    }

    public void setDataWC(List<String> dataWC) {
        this.dataWC = dataWC;
    }

    public ReportWC getReportWC() {
        return reportWC;
    }

    public void setReportWC(ReportWC reportWC) {
        this.reportWC = reportWC;
    }
}
