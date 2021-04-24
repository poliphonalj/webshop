package webshop.Model.UsersandRole;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Columns;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    @Override
    public String toString() {
        String postFix = null;
        if (addressType == AddressType.HOME_ADDRESS) {
            postFix = "_home";
        } else if (addressType == AddressType.DELIVERY_ADDRESS) {
            postFix = "_delivery";
        } else if (addressType == AddressType.BILLING_ADDRESS) {
            postFix = "_billing";
        }

        String stringToParse =
               ","+ "\"city" + postFix + "\"" + ":" + "\"" + city + "\"" + "," +
                "\"postCode" + postFix + "\"" + ":" + "\"" + postCode + "\"" + "," +
                "\"simpleAddress" + postFix + "\"" + ":" + "\"" + simpleAddress + "\"" + "," +
                "\"comment" + postFix + "\"" + ":" + "\"" + comment + "\"";
        return stringToParse;
    }
}
