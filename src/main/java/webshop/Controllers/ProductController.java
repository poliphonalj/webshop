//az entitasokbol letrejott db bol le lehet kerdezni ok
//de beirashoz mar kell a db datasource url


package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.DTOs.EndOfPromotionDTO;
import webshop.DTOs.NewProductPriceDTO;
import webshop.DTOs.ProductsForPromotionDTO;
import webshop.DTOs.PromotedProductDTO;
import webshop.Exceptions.ProductAddedException;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Product.Product;
import webshop.Model.Product.Unit;
import webshop.Services.ProductService;

import java.time.Instant;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/time")
    public String getCurrentTime() {
        return "majom";
    }

    //-)
    @PostMapping("/product/new")
    public ResponseEntity<?> addProduct(@RequestBody Product product) throws ProductAddedException {//Spring converts JSON to user object following the fields
        try {
            productService.addProduct(product.getName(), product.getDescription(), product.getPrice(), product.getUnit(),
                    product.getLocale(), product.getCategoryID(), product.isInPromotion(), product.isOutOfStock(),
                    product.isOutOfSeason());
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    //-)
    //ID, promotedPrice, promotionDescription listat var a requestben
    @PostMapping("/product/setPromotion")//returns a list with the promoted items and their price
    public ResponseEntity<?> setPromotion(@RequestBody List<ProductsForPromotionDTO> list) {
        try {
            productService.setPromotion(list);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //-)
    @GetMapping("/product/promotedList")   //returns all the promoted items in a list
    public List<PromotedProductDTO> getPromotedList() {
        return productService.getPromotedList();
    }


    @PostMapping("/product/endOfPromotion")//finishes the list for the proted items, and add them a new price
    public ResponseEntity<?> setEndOfPromotion(@RequestBody List<EndOfPromotionDTO> list) {
        try {
            productService.setEndOfPromotion(list);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //-)
    @GetMapping("/product/unitsList")   //returns all the units for drop box
    public Unit[] getUnitsList() {
        Unit[] array = productService.getUnitsList();
        return array;
    }


    //-)
    @GetMapping("/product/unit/{productID}")   //returns the unit String for the suitable product
    public Unit getUnitByProductID(@PathVariable long productID) {
        Unit unit = productService.getUnitByProductID(productID);
        return unit;
    }

    //-)      needs a JSON LIST in the requestbody
    @PostMapping("/product/newPrice")//modifies the price of the LIST of products with a DTO
    public ResponseEntity<?> setNewPrice(@RequestBody List<NewProductPriceDTO> list) {
        try {
            productService.setNewPrice(list);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    //-)
    @GetMapping("/product/getPrice/{ID}")   //get the price of a product by Id
    public long getPrice(@PathVariable long ID) {
        return productService.getPrice(ID);
    }

    //-)
    @GetMapping("/product/getProductByID/{ID}")   //get  a product by Id
    public Product getProductByID( @PathVariable long ID) {
        return productService.getProductByID(ID);
    }


    //-)  contains alapján keres
    @GetMapping("/product/getProductByName/{name}")   //get  a list of products containing "name"
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

    //-)
    @GetMapping("/product/getProductsByCategoryID/{categoryID}")   //get  a list of products with a category ID, only actives
    public List<Product> getProductsByCategory(@PathVariable("categoryID") long categoryID) {
        return productService.getProductsByCategoryID(categoryID);
    }

    //-)
    @GetMapping("/product/getAllProducts")   //get  a list of all products-only the actives!!!!!!!!
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


    //TODO
    //  image eleresi uttal be ki

    //sold out
    //out of season



}
