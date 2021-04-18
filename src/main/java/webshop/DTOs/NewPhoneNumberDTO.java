package webshop.DTOs;

public class NewPhoneNumberDTO {
    private String PhoneNumber;
    private long userID;

    public NewPhoneNumberDTO() {
    }

    public NewPhoneNumberDTO(String phoneNumber, long userID) {
        PhoneNumber = phoneNumber;
        this.userID = userID;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
