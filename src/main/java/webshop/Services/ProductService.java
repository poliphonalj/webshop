package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.Product.Product;
import webshop.Model.Product.Unit;
import webshop.Repository.ProductRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Locale;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Transactional
    public void addProduct(String productName, String productDescription, long productPrice, Unit productUnit,
                           Locale productLocale, int productCategoryID, boolean productIsInPromotion,
                           boolean productIsOutOfStock, boolean productIsOutOfSeason
    ) {
        Product p = new Product();
        p.setName(productName);
        p.setDescription(productDescription);
        p.setPrice(productPrice);
        p.setUnit(productUnit);
        p.setLocale(productLocale);
        p.setCategoryID(productCategoryID);
        p.setInPromotion(productIsInPromotion);
        p.setOutOfStock(productIsOutOfStock);
        p.setOutOfSeason(productIsOutOfSeason);

        productRepo.saveAndFlush(p);

    }
}
