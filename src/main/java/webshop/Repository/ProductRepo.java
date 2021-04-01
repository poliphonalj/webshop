package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.Product.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product findProductByName(String productName);
    Product findProductByID(long ID);
    List<Product>findByIsInPromotion(boolean b);
}
