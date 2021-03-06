package webshop.Model.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="categories")
//@JsonIgnoreProperties({""})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long ID;

    private String categoryName;
    private long parentCategoryID;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<Category> subCategories = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getParentCategoryID() {
        return parentCategoryID;
    }

    public void setParentCategoryID(long parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }
}