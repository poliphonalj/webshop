package webshop.DTOs;

import webshop.Model.UsersandRole.*;

import javax.persistence.OneToMany;
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
    private List<Address> myAddressList;

    private String country;
    private String county;
    private String postCode;
    private String city;
    private String name;
    private String houseNumber;
    private PlaceType placeType;    //enum
    private String comment;
    private String simpleAddress;    //name+placeTypte+houseNumber
    private AddressType addressType;
    private MyUser myUser;
    private Role role;

    public NewUserDTO() {
    }

    public NewUserDTO(String firstName, String lastName, String username, String phoneNumber, LocalDateTime lastLoggedInAt, LocalDateTime lastPurchasedAt, boolean isActive, Locale locale, String password, Role role, List<Address> myAddressList, String country, String county, String postCode, String city, String name, String houseNumber, PlaceType placeType, String comment, String simpleAddress, AddressType addressType, MyUser myUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.lastLoggedInAt = lastLoggedInAt;
        this.lastPurchasedAt = lastPurchasedAt;
        this.isActive = isActive;
        this.locale = locale;
        this.password = password;
        this.role = role;

        this.myAddressList = myAddressList;
        this.country = country;
        this.county = county;
        this.postCode = postCode;
        this.city = city;
        this.name = name;
        this.houseNumber = houseNumber;
        this.placeType = placeType;
        this.comment = comment;
        this.simpleAddress = simpleAddress;
        this.addressType = addressType;
        this.myUser = myUser;
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSimpleAddress() {
        return simpleAddress;
    }

    public void setSimpleAddress(String simpleAddress) {
        this.simpleAddress = simpleAddress;
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
