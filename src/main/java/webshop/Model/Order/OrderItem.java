package webshop.Model.Order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.Contract;
import webshop.Model.Product.Unit;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name = "order_items")
@JsonIgnoreProperties({"order","locale","description","inPromotion","id"})
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private long price;
    private long quantity;
    private Locale locale;
    private boolean isInPromotion;
    private Unit unit;
    private long productID;


    @ManyToOne
    private Order order;

    @Contract(pure = true)
    public OrderItem() {
    }

    public OrderItem(String name, String description, long price, int quantity, Locale locale, boolean isInPromotion, Unit unit, long productID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.locale = locale;
        this.isInPromotion = isInPromotion;

        this.unit = unit;
        this.productID = productID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }
}

