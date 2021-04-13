package webshop.Model.UsersandRole;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@JsonIgnoreProperties({"myUser"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String country;
    private String county;
    private String postCode;
    private String city;
    private String name;
    private String houseNumber;
    private PlaceType placeType;    //enum
    private String comment;
    private String simpleAddress;    //name+placeTypte+houseNumber
    private AddressType addressType;    //enum

    @ManyToOne
    MyUser myUser;

    public Address() {
    }

    public Address(String country, String county, String postCode, String city, String name, String houseNumber, PlaceType placeType, String comment, String simpleAddress, AddressType addressType) {
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

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }


}
