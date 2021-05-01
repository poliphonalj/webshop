package webshop.DTOs;

import webshop.Model.Order.OrderItem;
import webshop.Model.Order.PaymentType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private LocalDateTime orderTime;
    private PaymentType paymentType;
    private boolean status;
    private long deliveryFee;
    //////////////////////////////////////deliveryTime!!!!!!
    @NotEmpty
    //@Pattern(regexp = "kun.*")
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    @NotNull
    private String username;//email
    private String phoneNumber;
    @NotNull
    private String simpleAddress_delivery;
    private long postCode;
    private String city;
    private String comment;
    private long totalProductPrice;
    private long totalPrice;
    private long deliveryDayID;
    private long deliveryGapsID;
    private String deliveryGapString;

    List<OrderItem>itemList;

    private String sumProductPrice;

    public OrderDTO(LocalDateTime orderTime, PaymentType paymentType, boolean status, long deliveryFee, @NotEmpty String firstName, @NotEmpty String lastName, @Email @NotNull String username, String phoneNumber, @NotNull String simpleAddress_delivery, long postCode, String city, String comment, long totalProductPrice, long totalPrice, long deliveryDayID, long deliveryGapsID, List<OrderItem> itemList) {
        this.orderTime = orderTime;
        this.paymentType = paymentType;
        this.status = status;
        this.deliveryFee = deliveryFee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.simpleAddress_delivery = simpleAddress_delivery;
        this.postCode = postCode;
        this.city = city;
        this.comment = comment;
        this.totalProductPrice = totalProductPrice;
        this.totalPrice = totalPrice;
        this.deliveryDayID = deliveryDayID;
        this.deliveryGapsID = deliveryGapsID;
        this.itemList = itemList;
    }

    public OrderDTO() {
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(long deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSimpleAddress_delivery() {
        return simpleAddress_delivery;
    }

    public void setSimpleAddress_delivery(String simpleAddress_delivery) {
        this.simpleAddress_delivery = simpleAddress_delivery;
    }

    public long getPostCode() {
        return postCode;
    }

    public void setPostCode(long postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public long getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(long totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getDeliveryDayID() {
        return deliveryDayID;
    }

    public void setDeliveryDayID(long deliveryDayID) {
        this.deliveryDayID = deliveryDayID;
    }

    public long getDeliveryGapsID() {
        return deliveryGapsID;
    }

    public void setDeliveryGapsID(long deliveryGapsID) {
        this.deliveryGapsID = deliveryGapsID;
    }

    public String getDeliveryGapString() {
        return deliveryGapString;
    }

    public void setDeliveryGapString(String deliveryGapString) {
        this.deliveryGapString = deliveryGapString;
    }
}
