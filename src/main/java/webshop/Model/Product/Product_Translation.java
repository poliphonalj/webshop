package webshop.Model.Product;

import javax.persistence.*;

@Entity
@Table(name="product_translations")

public class Product_Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private Language languages;

    @ManyToOne
    Product product;

    public Product_Translation() {
    }

    public Product_Translation(Language languages) {
        this.languages = languages;
    }

    public Language getLanguages() {
        return languages;
    }

    public void setLanguages(Language language) {
        this.languages = language;
    }
}
