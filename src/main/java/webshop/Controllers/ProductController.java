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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> getPromotedList() {
        List<PromotedProductDTO> list =(productService.getPromotedList());
        if (!(list.isEmpty())) {
            HashMap<String, List<PromotedProductDTO>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
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
    public ResponseEntity<?> getUnitsList() {
        Unit[] array = productService.getUnitsList();
        if (array.length != 0) {
            HashMap<String, Unit[]> hMap = new HashMap<>();
            hMap.put("list", array);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }


    //-)
    @GetMapping("/product/unit/{productID}")   //returns the unit String for the suitable product
    public ResponseEntity<?> getUnitByProductID(@PathVariable long productID) {
        Unit unit = productService.getUnitByProductID(productID);
        if (unit != null) {
            HashMap<String, Unit> hMap = new HashMap<>();
            hMap.put("list", unit);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
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
    public ResponseEntity<?> getPrice(@PathVariable long ID) {
        List<Long> list = new ArrayList<>();
        list.add(productService.getPrice(ID));
        if (!(list.isEmpty())) {
            HashMap<String, List<Long>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }


    //-)
    @GetMapping("/product/getProductByID/{ID}")   //get  a product by Id
    public ResponseEntity<?> getProductByID(@PathVariable long ID) {
        List<Product> list = new ArrayList<>();
        list.add(productService.getProductByID(ID));
        return standartisedReturn(list);
    }


    //-)  contains alapján keres
    @GetMapping("/product/getProductByName/{name}")   //get  a list of products containing "name"
    public ResponseEntity<?> getProductsByName(@PathVariable String name) {
        List<Product> list = productService.getProductsByName(name);
        return standartisedReturn(list);
    }

    //-)
    @GetMapping("/product/getProductsByCategoryID/{categoryID}")
    //get  a list of products with a category ID, only actives
    public ResponseEntity<?> getProductsByCategory(@PathVariable("categoryID") long categoryID) {
        List<Product> list = productService.getProductsByCategoryID(categoryID);
        return standartisedReturn(list);
    }

    //-)
    @GetMapping("/product/getAllProducts")   //get  a list of all products-only the actives!!!!!!!!
    public ResponseEntity<?> getAllProducts() {
        List<Product> list = productService.getAllProducts();
       // if (!(list.isEmpty())) {
         //   HashMap<String, List<Product>> hMap = new HashMap<>();
           // hMap.put("list", list);
            //return ResponseEntity.ok().body(hMap);}
       return standartisedReturn(list);
       // return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }


    public ResponseEntity<?> standartisedReturn(List<Product> list) {
       if (!(list.isEmpty())) {
            HashMap<String, List<Product>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);

        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }
}
