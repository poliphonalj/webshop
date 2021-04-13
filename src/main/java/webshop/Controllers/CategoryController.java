//TODO
//one product should have more categories???????

package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webshop.DTOs.PromotedProductDTO;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Product.Category;
import webshop.Model.Slogan;
import webshop.Services.CategoryService;

import java.util.HashMap;
import java.util.List;

@RestController
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category/new")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            // if(myUserDetailsService.loadUserByUsername(newUserDTO.getUsername())==null){
            categoryService.addCategory(category);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
            //   }
            //throw new UserExistException();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @GetMapping("/category/list")
    public ResponseEntity<?> listCategory() {
        List<Category> list = categoryService.listCategory();
        if (!(list.isEmpty())) {
            HashMap<String, List<Category>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }
}
