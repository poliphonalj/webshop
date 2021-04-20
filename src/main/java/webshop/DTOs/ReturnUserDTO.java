package webshop.DTOs;

import webshop.Model.UsersandRole.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class ReturnUserDTO {

    private long ID;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private LocalDateTime lastLoggedInAt;
    private LocalDateTime lastPurchasedAt;
    private boolean isActive;
    private Locale locale;
    private long numberOfPurchase;
    private Role role;
    //private List<Address> myAddressList;

    //private String country;
    //private String county;
  //  private String postCode;
   // private String city;
    //private String name;
    //private String houseNumber;
    // private PlaceType placeType;    //enum
   // private String comment;
   // private String simpleAddress;    //name+placeTypte+houseNumber
   // private AddressType addressType;
   // private MyUser myUser;

    public ReturnUserDTO() {
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

    public long getNumberOfPurchase() {
        return numberOfPurchase;
    }

    public void setNumberOfPurchase(long numberOfPurchase) {
        this.numberOfPurchase = numberOfPurchase;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
