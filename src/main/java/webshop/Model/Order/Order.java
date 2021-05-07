package webshop.Model.Order;

import org.hibernate.annotations.Cascade;
import webshop.Model.UsersandRole.MyUser;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private LocalDateTime orderTime;
    private PaymentType paymentType;
    private boolean status;
    private long deliveryFee;
    private long productID;
    private String productName;
    
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

    @ManyToOne
    MyUser myUser;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)

    @NotEmpty
    private List<OrderItem> ordersItemList;

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

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
    
}
