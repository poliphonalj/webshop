package webshop.DTOs;

public class ProductsForPromotionDTO {
    private long ID;
    private long promotedPrice;
    private String promotionDescription;

    public ProductsForPromotionDTO(long ID, long promotedPrice, String promotionDescription) {
        this.ID = ID;
        this.promotedPrice = promotedPrice;
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

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }
}
