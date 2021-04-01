package webshop.DTOs;

public class EndOfPromotionDTO {
    private long ID;
    private long newPrice;

    public EndOfPromotionDTO(long ID, long newPrice) {
        this.ID = ID;
        this.newPrice = newPrice;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(long newPrice) {
        this.newPrice = newPrice;
    }
}
