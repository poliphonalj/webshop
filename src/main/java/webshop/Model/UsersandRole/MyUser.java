/*
THis class represents the user table in the db. It connects to Roles table with a one-many relation
It even connects to the address table with a one to many relation.
 */




package webshop.Model.UsersandRole;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import webshop.Model.Order.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name="users")
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private LocalDateTime lastLoggedInAt;
    private LocalDateTime lastPurchasedAt;
    private boolean isActive;
    private Locale locale;
    private String password;

    @OneToMany(mappedBy = "myUser")
    private List<Role> roleList;

    @OneToMany(mappedBy = "myUser")
    private List<Address> myAddressList;

    @OneToMany(mappedBy = "myUser")
    private List<Order> orderList;


    public MyUser() {}

    public MyUser(String firstName, String lastName, String email, int phoneNumber,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }



    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Address> getMyAddressList() {
        return myAddressList;
    }

    public void setMyAddressList(List<Address> myAddressList) {
        this.myAddressList = myAddressList;
    }
}
