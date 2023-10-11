package de.unmuth;

public enum OrderStatus {
    PROCESSING("progressing"),
    IN_DELIVERY("in delivery"),
    COMPLETED("compleded");

    public final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}


