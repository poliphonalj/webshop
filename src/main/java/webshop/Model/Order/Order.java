package webshop.Model.Order;

import webshop.Model.UsersandRole.MyUser;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;


//TODO status beallitasa

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private long deliveryDayID;
    private long deliveryGapsID;


    private LocalDateTime orderTime;
    private PaymentType paymentType;
    private boolean status=false;
    private long deliveryFee;

    private String productName;
    
    //@NotEmpty
    //@Pattern(regexp = "kun.*")
    private String firstName;
    //@NotEmpty
    private String lastName;
    
    //@Email
    //@NotNull
    private String username;//email
    
    private String phoneNumber;
    
    //@NotNull
    private String simpleAddress_delivery;

    private long postCode;

    private String city;

    private String comment;

    @ManyToOne
    MyUser myUser;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)

    @NotEmpty
    private List<OrderItem> ordersItemList;


    public Order() {
    }

    public Order(long id, long deliveryDayID, long deliveryGapsID, LocalDateTime orderTime, PaymentType paymentType, boolean status, long deliveryFee, String productName, String firstName,  String lastName,  String username, String phoneNumber,  String simpleAddress_delivery, long postCode, String city, String comment, MyUser myUser, @NotEmpty List<OrderItem> ordersItemList) {
        this.id = id;
        this.deliveryDayID = deliveryDayID;
        this.deliveryGapsID = deliveryGapsID;
        this.orderTime = orderTime;
        this.paymentType = paymentType;
        this.status = status;
        this.deliveryFee = deliveryFee;
        this.productName = productName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.simpleAddress_delivery = simpleAddress_delivery;
        this.postCode = postCode;
        this.city = city;
        this.comment = comment;
        this.myUser = myUser;
        this.ordersItemList = ordersItemList;
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


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String userFirstName) {
        this.firstName = userFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String userLastName) {
        this.lastName = userLastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userEmail) {
        this.username = userEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String userPhone) {
        this.phoneNumber = userPhone;
    }

    public String getSimpleAddress_delivery() {
        return simpleAddress_delivery;
    }

    public void setSimpleAddress_delivery(String userAddress) {
        this.simpleAddress_delivery = userAddress;
    }

	public long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
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

    public MyUser getMyUser() {
		return myUser;
	}

	public void setMyUser(MyUser myUser) {
		this.myUser = myUser;
	}

	public List<OrderItem> getOrdersItemList() {
		return ordersItemList;
	}

	public void setOrdersItemList(List<OrderItem> ordersItemList) {
		this.ordersItemList = ordersItemList;
	}

    public long getDeliveryDayID() {
        return deliveryDayID;
    }

    public void setDeliveryDayID(long deliverDayID) {
        this.deliveryDayID = deliverDayID;
    }

    public long getDeliveryGapsID() {
        return deliveryGapsID;
    }

    public void setDeliveryGapsID(long deliveryGapsID) {
        this.deliveryGapsID = deliveryGapsID;
    }
}
