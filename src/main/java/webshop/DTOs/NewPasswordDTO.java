package webshop.DTOs;

public class NewPasswordDTO {
    public String password;
    public long userID;

    public NewPasswordDTO() {
    }

    public NewPasswordDTO(String password, long userID) {
        this.password = password;
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
