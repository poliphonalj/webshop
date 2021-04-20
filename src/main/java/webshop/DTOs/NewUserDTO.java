package webshop.DTOs;

import webshop.Model.UsersandRole.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class NewUserDTO {
    private long ID;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private LocalDateTime lastLoggedInAt;
    private LocalDateTime lastPurchasedAt;
    private boolean isActive;
    private Locale locale;
    private String password;
    private Role role;
    private List<Address> myAddressList;

    private String country;
    private String county;
    private String postCode_home;
    private String postCode_delivery;
    private String postCode_billing;

    private String city_home;
    private String city_delivery;
    private String city_billing;

    private String name;
    private String houseNumber;
    private PlaceType placeType;    //enum
    private String comment_home;
    private String comment_delivery;
    private String comment_billing;
    private String address_home;//name+placeTypte+houseNumber
    private String address_delivery;
    private String address_billing;

    private AddressType addressType;
    private MyUser myUser;

    public NewUserDTO() {
    }

    public String getPostCode_home() {
        return postCode_home;
    }

    public void setPostCode_home(String postCode_home) {
        this.postCode_home = postCode_home;
    }

    public String getPostCode_delivery() {
        return postCode_delivery;
    }

    public void setPostCode_delivery(String postCode_delivery) {
        this.postCode_delivery = postCode_delivery;
    }

    public String getPostCode_billing() {
        return postCode_billing;
    }

    public void setPostCode_billing(String postCode_billing) {
        this.postCode_billing = postCode_billing;
    }

    public String getCity_home() {
        return city_home;
    }

    public void setCity_home(String city_home) {
        this.city_home = city_home;
    }

    public String getCity_delivery() {
        return city_delivery;
    }

    public void setCity_delivery(String city_delivery) {
        this.city_delivery = city_delivery;
    }

    public String getCity_billing() {
        return city_billing;
    }

    public void setCity_billing(String city_billing) {
        this.city_billing = city_billing;
    }

    public String getComment_home() {
        return comment_home;
    }

    public void setComment_home(String comment_home) {
        this.comment_home = comment_home;
    }

    public String getComment_delivery() {
        return comment_delivery;
    }

    public void setComment_delivery(String comment_delivery) {
        this.comment_delivery = comment_delivery;
    }

    public String getComment_billing() {
        return comment_billing;
    }

    public void setComment_billing(String comment_billing) {
        this.comment_billing = comment_billing;
    }

    public String getAddress_home() {
        return address_home;
    }

    public void setAddress_home(String address_home) {
        this.address_home = address_home;
    }

    public String getAddress_delivery() {
        return address_delivery;
    }

    public void setAddress_delivery(String address_delivery) {
        this.address_delivery = address_delivery;
    }

    public String getAddress_billing() {
        return address_billing;
    }

    public void setAddress_billing(String address_billing) {
        this.address_billing = address_billing;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public LocalDateTime getLastLoggedInAt() {
        return lastLoggedInAt;
    }

    public void setLastLoggedInAt(LocalDateTime lastLoggedInAt) {
        this.lastLoggedInAt = lastLoggedInAt;
    }

    public LocalDateTime getLastPurchasedAt() {
        return lastPurchasedAt;
    }

    public void setLastPurchasedAt(LocalDateTime lastPurchasedAt) {
        this.lastPurchasedAt = lastPurchasedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Address> getMyAddressList() {
        return myAddressList;
    }

    public void setMyAddressList(List<Address> myAddressList) {
        this.myAddressList = myAddressList;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }



    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }
}
