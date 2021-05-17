package webshop.DTOs;

import webshop.Model.Orders.PaymentType;

public class OrderByGapsDTO {

private long ID;
private PaymentType paymentType;
private long postCode_delivery;
private String simpleaddress_delivery;
private long deliveryGapsID;
private String deliveryGapString;

    public OrderByGapsDTO() {
    }

    public long getID() {
        return ID;
    }

    public String getDeliveryGapString() {
        return deliveryGapString;
    }

    public void setDeliveryGapString(String deliveryGapString) {
        this.deliveryGapString = deliveryGapString;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public long getPostCode_delivery() {
        return postCode_delivery;
    }

    public void setPostCode_delivery(long postCode_delivery) {
        this.postCode_delivery = postCode_delivery;
    }

    public String getSimpleaddress_delivery() {
        return simpleaddress_delivery;
    }

    public void setSimpleaddress_delivery(String simpleaddress_delivery) {
        this.simpleaddress_delivery = simpleaddress_delivery;
    }

    public long getDeliveryGapsID() {
        return deliveryGapsID;
    }

    public void setDeliveryGapsID(long deliveryGapsID) {
        this.deliveryGapsID = deliveryGapsID;
    }
}
