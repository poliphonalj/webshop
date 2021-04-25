package webshop.DTOs;

import webshop.Model.UsersandRole.AddressType;

public class NewAddressDTO {
    private long userID;
    private long postCode=0;
    private String simpleAddress;
    private String comment;
    private String city;
    private AddressType addressType;

    public NewAddressDTO(long userID, long postCode, String simpleAddress, String comment, AddressType addressType, String city) {
        this.userID = userID;
        this.postCode = postCode;
        this.simpleAddress = simpleAddress;
        this.comment = comment;
        this.addressType = addressType;
        this.city=city;
    }

    public NewAddressDTO() {
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getPostCode() {
        return postCode;
    }

    public void setPostCode(long postCode) {
        this.postCode = postCode;
    }

    public String getSimpleAddress() {
        return simpleAddress;
    }

    public void setSimpleAddress(String simpleAddress) {
        this.simpleAddress = simpleAddress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
