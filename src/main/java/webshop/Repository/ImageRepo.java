package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.Product.Image;
import webshop.Model.Product.Product;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findImageByIDAndIsActiveTrue(long ID);
    Image findByNameAndIsActiveTrue(String imageName);
    List<Image>findImagesByProductIDAndIsActiveTrue(long ID);
    
}
