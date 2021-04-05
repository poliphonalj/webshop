package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Product.Image;
import webshop.Services.ImageService;

@RestController
public class ImageController {
    private final ImageService imageservice;

    @Autowired
    public ImageController(ImageService imageservice) {
        this.imageservice = imageservice;
    }

    @PostMapping("/image/new")
        public ResponseEntity<?> addImage(@RequestBody Image image){
           try{
            imageservice.addImage(image);
               return ResponseEntity.ok(new FeedbackToFrontend(true));
           } catch (Exception e) {
               return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
           }
    }

}
