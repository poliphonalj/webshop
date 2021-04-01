package webshop.DTOs;

public class NewProductPriceDTO {
    long ID;
    long newPrice;

    public NewProductPriceDTO(long ID, long newPrice) {
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
