package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.Model.Product.Image;
import webshop.Model.Product.Product;

import java.util.List;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findImageByIDAndIsActiveTrue(long ID);
    Image findByNameAndIsActiveTrue(String imageName);
    List<Image>findImagesByProductIDAndIsActiveTrue(long ID);
}
