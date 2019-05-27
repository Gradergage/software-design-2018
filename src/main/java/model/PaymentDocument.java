package model;

public class PaymentDocument {
    private long id;
    private long totalCost;
    private long workCost;
    private long deviceCost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
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
}
