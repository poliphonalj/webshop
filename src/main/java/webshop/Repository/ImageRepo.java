package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.Model.Product.Image;
import webshop.Model.Product.Product;

public interface ImageRepo extends JpaRepository<Image, Integer> {
    Image findImageByID(long ID);
}
