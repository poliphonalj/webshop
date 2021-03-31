package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.Product.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product findProductByName(String productName);
}
