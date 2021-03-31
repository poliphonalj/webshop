package webshop.Model.Order;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name = "order_items")
public class Order_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String name;
    private String description;
    private long price;
    private int quantity;
    private Locale locale;
    private boolean isInPromotion;

    @ManyToOne
    private Order order;

    public Order_Item() {
    }

    public Order_Item(String name, String description, long price, int quantity, Locale locale, boolean isInPromotion) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.locale = locale;
        this.isInPromotion = isInPromotion;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
}

