package webshop.DTOs;

public class UserRatingDTO {
    private long userID;
    private String rate;

    public UserRatingDTO() {
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
