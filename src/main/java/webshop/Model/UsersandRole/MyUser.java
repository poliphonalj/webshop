/*
THis class represents the user table in the db. It connects to Roles table with a one-many relation
It even connects to the address table with a one to many relation.
 */




package webshop.Model.UsersandRole;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import webshop.Model.Order.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name="users")
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private LocalDateTime lastLoggedInAt;
    private LocalDateTime lastPurchasedAt;
    private boolean isActive;
    private Locale locale;
    private String password;
    private long numberOfPurchase;
    private String resetPasswordToken;
    private String userRates;



    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "users_ID"),
            inverseJoinColumns = @JoinColumn(name = "roles_ID"))
    private List<Role> roleList;


    @OneToMany(mappedBy = "myUser")
    private List<Address> myAddressList;

    @OneToMany(mappedBy = "myUser")
    private List<Order> orderList;


    public MyUser() {}

    public MyUser(String firstName, String lastName, String username, String phoneNumber,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password=password;
        this.numberOfPurchase=0;
        this.isActive=true;

    }



    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
    this.password=password;
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

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role actualRole : roleList) {
            authorities.add(new SimpleGrantedAuthority(actualRole.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUserRates() {
        return userRates;
    }

    public void setUserRates(String userRates) {
        this.userRates = userRates;
    }
}
