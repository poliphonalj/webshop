package webshop.DTOs;

public class PromotedProductDTO {
    private long ID;
    private long promotedPrice;
    private long price; //original price
    private String promotionDescription;

    public PromotedProductDTO(long ID, long promotedPrice, long price, String promotionDescription) {
        this.ID = ID;
        this.promotedPrice = promotedPrice;
        this.price = price;
        this.promotionDescription = promotionDescription;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getPromotedPrice() {
        return promotedPrice;
    }

    public void setPromotedPrice(long promotedPrice) {
        this.promotedPrice = promotedPrice;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }
}
