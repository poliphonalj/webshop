package webshop.Model.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String name;
    private String description;
    private long price;
    private Locale locale;
    private boolean isInPromotion;
    private boolean isOutOfStock;
    private boolean isOutOfSeason;
    private Unit unit;
    private long categoryID;

    private long promotedPrice;
    private String promotionDescription;

    @OneToMany(mappedBy = "product")
    private List<Image> imageList;

    @OneToMany(mappedBy = "product")//ez a mappeles a masik osztaly fieldjere vonatkozik
    private List<Product_Translation> tranlationsList;

    @ManyToOne
    Category category;

    public Product() {
    }

    public Product(String name, String description, long price,Unit unit, Locale locale, long categoryID,
                   boolean isInPromotion, boolean isOutOfStock, boolean isOutOfSeason) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit=unit;
        this.locale = locale;
        this.categoryID=categoryID;
        this.isInPromotion = isInPromotion;
        this.isOutOfStock = isOutOfStock;
        this.isOutOfSeason = isOutOfSeason;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
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
}
