package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Product.Image;
import webshop.Services.ImageService;

import java.io.IOException;

@RestController
public class ImageController {
    private final ImageService imageservice;

    @Autowired
    public ImageController(ImageService imageservice) {
        this.imageservice = imageservice;
    }

    @PostMapping("/image/new")
        public ResponseEntity<?> addImage(@RequestBody MultipartFile file, long productID, String description, String tooltip) throws IOException {
           try{
            imageservice.addImage(file, productID, description, tooltip);
               return ResponseEntity.ok(new FeedbackToFrontend(true));
           } catch (Exception e) {
               return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
           }
    }

    @PostMapping("/image/get/{imageName}")
    public ResponseEntity<?> getImageByName(@RequestParam String imageName) throws IOException {
        try{
            imageservice.getImageByName(imageName);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PostMapping("/image/get/{ID}")
    public ResponseEntity<?> getImageByID(@RequestParam long ID) throws IOException {
        try{
            imageservice.getImageByID(ID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

}
