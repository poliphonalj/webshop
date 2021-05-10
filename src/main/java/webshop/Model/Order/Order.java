package webshop.Model.Order;

import webshop.Model.UsersandRole.MyUser;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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

    private long postCode_delivery;

    private String city_delivery;

    private String comment_delivery;

    @ManyToOne
    MyUser myUser;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)

    @NotEmpty
    private List<OrderItem> ordersItemList;


    public Order() {
    }

    public Order(long id, long deliveryDayID, long deliveryGapsID, LocalDateTime orderTime, PaymentType paymentType, boolean status, long deliveryFee, String productName, String firstName, String lastName, String username, String phoneNumber, String simpleAddress_delivery, long postCode_delivery, String city_delivery, String comment_delivery, MyUser myUser, @NotEmpty List<OrderItem> ordersItemList) {
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
        this.postCode_delivery = postCode_delivery;
        this.city_delivery = city_delivery;
        this.comment_delivery = comment_delivery;
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

    public long getPostCode_delivery() {
        return postCode_delivery;
    }

    public void setPostCode_delivery(long postCode_delivery) {
        this.postCode_delivery = postCode_delivery;
    }

    public String getCity_delivery() {
        return city_delivery;
    }

    public void setCity_delivery(String city_delivery) {
        this.city_delivery = city_delivery;
    }

    public String getComment_delivery() {
        return comment_delivery;
    }

    public void setComment_delivery(String comment_delivery) {
        this.comment_delivery = comment_delivery;
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
