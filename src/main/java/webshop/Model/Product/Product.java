package webshop.Model.Product;

import javax.persistence.*;
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
    private int categoryID;

    @OneToMany(mappedBy = "product")
    private List<Image> imageList;

    @OneToMany(mappedBy = "product")//ez a mappeles a masik osztaly fieldjere vonatkozik
    private List<Product_Translation> tranlationsList;

    @ManyToOne
    Category category;

    public Product() {
    }

    public Product(String name, String description, long price, Locale locale, boolean isInPromotion, boolean isOutOfStock, boolean isOutOfSeason, Unit unit, int categoryID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.locale = locale;
        this.isInPromotion = isInPromotion;
        this.isOutOfStock = isOutOfStock;
        this.isOutOfSeason = isOutOfSeason;
        this.unit=unit;
        this.categoryID=categoryID;
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
