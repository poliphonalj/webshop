package webshop.Controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webshop.DTOs.ImageDTO;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Product.Image;
import webshop.Services.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ImageController {
    private final ImageService imageservice;

    @Autowired
    public ImageController(ImageService imageservice) {
        this.imageservice = imageservice;
    }





    @CrossOrigin
   @PostMapping("/image/new/({productID})")
    public ResponseEntity<?>  newImage(@RequestParam MultipartFile file, @PathVariable long productID) throws IOException {
        imageservice.addImage(file,productID);
        try{
            imageservice.addImage(file, productID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

        /* ez a jo
        public ResponseEntity<?> addImage(@RequestBody MultipartFile file, long productID) throws IOException {
           try{
            imageservice.addImage(file, productID);
               return ResponseEntity.ok(new FeedbackToFrontend(true));
           } catch (Exception e) {
               return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
           }


    }
*/
    @GetMapping("/image/getByName/{imageName}")
    public ResponseEntity<?> getImageByName(@PathVariable String imageName) throws IOException {
        try{
            JSONObject jObj=   imageservice.getImageByName(imageName);
            return ResponseEntity.ok(jObj);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @GetMapping("/image/getByID/{imageID}")
    public ResponseEntity<?> getImageByID(@PathVariable long imageID) throws IOException {
        try{
            JSONObject jObj= imageservice.getImageByID(imageID);
            return ResponseEntity.ok(jObj);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //csoportos lekerdezes->tombot ad vissza
    @GetMapping("/image/getByProductID/{productID}")
    public ResponseEntity<?> getImageByProductID(@PathVariable long productID) throws IOException {//lehetne requestparam is
        try{
            JSONObject jObj=imageservice.getImagesByProductID(productID);
            return ResponseEntity.ok(jObj);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PutMapping("/image/removeByProductID/{productID}")
    public ResponseEntity<?> removeImageByProductID(@PathVariable long productID) throws IOException {//lehetne requestparam is
        try{
           imageservice.removeImagesByProductID(productID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    @PutMapping("/image/removeByID/{imageID}")
    public ResponseEntity<?> removeImageByID(@PathVariable long productID) throws IOException {
        try{
            imageservice.removeImagesByProductID(productID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

}
