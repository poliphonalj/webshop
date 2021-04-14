//this class is valid for adding new product with image and category to the sys.


package webshop.DTOs;

import webshop.Model.Product.*;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Locale;

public class NewProductDTO {

//product
    private long ID;
    private String productName;
    private String productDescription;
    private long price;
    private Locale locale;
    private boolean isInPromotion;
    private boolean isOutOfStock;
    private boolean isOutOfSeason;
   // private boolean isActive;
    private Unit unit;
    private long promotedPrice;
    private String promotionDescription;
    private List<Image> imageList;
    private List<Product_Translation> tranlationsList;
    private Category category;

//image
    private String imageName;
    private String type;
    private String imageDescription;
    private String tooltip;
    private long productID;
    private byte[] byteFlow;
    //private boolean isActive=true;
   // private Product product;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isInPromotion() {
        return isInPromotion;
    }

    public void setInPromotion(boolean inPromotion) {
        isInPromotion = inPromotion;
    }

    public boolean isOutOfStock() {
        return isOutOfStock;
    }

    public void setOutOfStock(boolean outOfStock) {
        isOutOfStock = outOfStock;
    }

    public boolean isOutOfSeason() {
        return isOutOfSeason;
    }

    public void setOutOfSeason(boolean outOfSeason) {
        isOutOfSeason = outOfSeason;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Product_Translation> getTranlationsList() {
        return tranlationsList;
    }

    public void setTranlationsList(List<Product_Translation> tranlationsList) {
        this.tranlationsList = tranlationsList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public byte[] getByteFlow() {
        return byteFlow;
    }

    public void setByteFlow(byte[] byteFlow) {
        this.byteFlow = byteFlow;
    }
}
