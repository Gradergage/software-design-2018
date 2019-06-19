package model;

public class ModelTypes {

    public static final int TYPE_USER_OPERATOR_CC = 0;
    public static final int TYPE_USER_OPERATOR_WC = 1;
    public static final int TYPE_USER_OPERATOR_TC = 2;
    public static final int TYPE_USER_CUSTOMER = 4;

    public static final int ORDER_STATUS_IDLE = 0;
    public static final int ORDER_STATUS_ACTIVE = 1;
    public static final int ORDER_STATUS_COMPLETED = 2;
    public static final int ORDER_STATUS_WAITING = 3;

    public static final int ORDER_PAYMENT_STATUS_IDLE = 0;
    public static final int ORDER_PAYMENT_STATUS_CONFIRMED = 1;
    public static final int ORDER_PAYMENT_STATUS_REJECTED = 2;
    public static final int ORDER_PAYMENT_STATUS_WAITING = 3;

    public static String getStringStatus(Order order) {
        Integer t, p, w;
        w = null;
        String res = "Unknown status";
        t = order.getStatus();
        p = order.getPaymentStatus();
        if (order.getWorkOrder() != null)
            w = order.getWorkOrder().getStatus();


        if (p == 2)
            res = "rejected";
        else if (t == 0 && p == 0)
            res = "waiting for applying";
        else if (t == 1 && p == 0)
            res = "in process";
        else if (t == 2 && p == 0)
            res = "awaiting payment document";
        else if (t == 2 && p == 3)
            res = "payment awaiting";
        else if (t == 2 && p == 1)
            res = "in process, paid";

        if (w != null)
            if (t == 2 && p == 1 && w == 1)
                res = "in process, paid";
            else if (t == 2 && w == 3)
                res = "waiting for close";
            else if (t == 2 && w == 2) res = "completed";

        return res.toUpperCase();
    }

    public static Boolean isNeedPayment(Order order) {
        Integer t, p;
        t = order.getStatus();
        p = order.getPaymentStatus();
        return (t == 2 && p == 3);
    }
}
