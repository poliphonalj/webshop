package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import webshop.DTOs.ProductsForPromotionDTO;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Slogan;
import webshop.Services.SloganService;

import java.util.List;

public class SloganController {
    private SloganService sloganService;

    @Autowired
    public SloganController(SloganService sloganService) {
        this.sloganService = sloganService;
    }

    @GetMapping("/slogan/random")   //return a random slogan from the db
    public Slogan getRandom(){
        return sloganService.getRandomSlogan();
    }

    @PostMapping("/slogan/add") //adding a slogan to thee db
    public ResponseEntity<?> addSlogan(@RequestBody String slogan ) {
        try {
            sloganService.addSlogan(slogan);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


}
