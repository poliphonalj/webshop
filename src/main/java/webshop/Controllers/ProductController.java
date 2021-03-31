package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webshop.Exceptions.ProductAddedException;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Product.Product;
import webshop.Repository.ProductRepo;
import webshop.Services.ProductService;

@RestController
public class ProductController {

private final ProductService productService;
private final ProductRepo productRepo;

    @Autowired
    public ProductController(ProductService productService, ProductRepo productRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
    }

    @PostMapping("/product/new")
    public ResponseEntity<?> addProduct(@RequestBody Product product) throws ProductAddedException {//Spring converts JSON to user object following the fields
        try {
            productService.addProduct(product.getName(),product.getDescription(), product.getPrice(),product.getUnit(),
                   product.getLocale(),product.getCategoryID(), product.isInPromotion(),product.isOutOfStock(),
                   product.isOutOfSeason());
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }



}
