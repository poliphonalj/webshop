package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.DTOs.ProductsForPromotionDTO;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Product.Category;
import webshop.Model.Product.Product;
import webshop.Model.Slogan;
import webshop.Services.SloganService;

import java.util.HashMap;
import java.util.List;

@RestController
public class SloganController {
    private SloganService sloganService;

    @Autowired
    public SloganController(SloganService sloganService) {
        this.sloganService = sloganService;
    }

    //ok
    @GetMapping("/slogan/random")   //return a random slogan from the db
    public ResponseEntity<?> getRandom() {
        Slogan s = sloganService.getRandomSlogan();
        if (s != null) {
            HashMap<String, Slogan> hMap = new HashMap<>();
            hMap.put("list", s);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }

    //ok
    @PostMapping("/slogan/add") //adding a slogan to thee db
    public ResponseEntity<?> addSlogan(@RequestBody String slogan) {
        try {
            sloganService.addSlogan(slogan);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //ok
    @PostMapping("/slogan/remove/{ID}") //remove a slogan from thee db
    public ResponseEntity<?> removeSlogan(@PathVariable long ID) {
        try {
            sloganService.removeSlogan(ID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //ok
    @GetMapping("/slogan/list")//if its empty??
    public ResponseEntity<?> listSlogan() {
        List<Slogan> list = sloganService.listSlogan();
        if (!(list.isEmpty())) {
            HashMap<String, List<Slogan>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }

    //ok
    @PostMapping("/slogan/reactivate/{sloganID}")
    public ResponseEntity<?> listSlogan(@PathVariable long sloganID) {
        try {
            sloganService.reactivate(sloganID);
            return ResponseEntity.ok().body(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }
}



