package webshop.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.Product.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Category findCategoryByCategoryName(String categoryName);
}