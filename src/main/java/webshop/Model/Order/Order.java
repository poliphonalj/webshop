package webshop.Model.Order;

import webshop.Model.UsersandRole.MyUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private LocalDateTime orderTime;
    private PaymentType paymentType;
    private boolean status;
    private long deliveryFee;
    private long product_ID;
    private String product_Name;
    private String user_FirstName;
    private String user_LastName;
    private String user_Email;
    private String user_Phone;
    private String user_Address;

    @ManyToOne
    MyUser myUser;

    @OneToMany(mappedBy = "order")
    private List<Order_Item> ordersItemList;

    public Order() {
    }

    public Order(LocalDateTime orderTime, PaymentType paymentType, boolean status,
                 long deliveryFee, long product_ID, String product_Name, String user_FirstName,
                 String user_LastName, String user_Email, String user_Phone, String user_Address) {
        this.orderTime = orderTime;
        this.paymentType = paymentType;
        this.status = status;
        this.deliveryFee = deliveryFee;
        this.product_ID = product_ID;
        this.product_Name = product_Name;
        this.user_FirstName = user_FirstName;
        this.user_LastName = user_LastName;
        this.user_Email = user_Email;
        this.user_Phone = user_Phone;
        this.user_Address = user_Address;
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

    public long getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(long product_ID) {
        this.product_ID = product_ID;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getUser_FirstName() {
        return user_FirstName;
    }

    public void setUser_FirstName(String user_FirstName) {
        this.user_FirstName = user_FirstName;
    }

    public String getUser_LastName() {
        return user_LastName;
    }

    public void setUser_LastName(String user_LastName) {
        this.user_LastName = user_LastName;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public String getUser_Phone() {
        return user_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
    }

    public String getUser_Address() {
        return user_Address;
    }

    public void setUser_Address(String user_Address) {
        this.user_Address = user_Address;
    }
}
