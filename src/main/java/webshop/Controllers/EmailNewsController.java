package webshop.Controllers;

import jdk.jfr.ContentType;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.WantEmailNews;
import webshop.Services.EmailService;
import webshop.Services.WantEmailService;

import java.util.List;

@RestController
public class EmailNewsController {
    @Autowired
    EmailService emailService;
    @Autowired
    WantEmailService wService;

    @PostMapping("/emailNews/sendANew")
    public ResponseEntity<?> sendEmailNews(@RequestBody JSONObject j) {
        try {

            String text=j.get("text").toString();

            List<String> emails = wService.getAllWhoWantsEmailNews();
           emailService.sendNews(text,emails);

            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @GetMapping("/emailNews/allActives")
    public ResponseEntity<?> getAllActives() {
        try {
            List<String> list = wService.getAllWhoWantsEmailNews();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

//jo
    //a regisztracio soran is be tud jelentkezni a mostani mukodes alapjan
    @PostMapping("/emailNews/signUp")
    public ResponseEntity<?> signUp(@RequestBody WantEmailNews w) {
        try {
            if (wService.wantEmailNewsByEmail(w.getEmail()) != null) {
                wService.reactivate(w.getEmail());
                return ResponseEntity.ok(new FeedbackToFrontend(true));
            } else {

                WantEmailNews w1 = new WantEmailNews();
                w1.setEmail(w.getEmail());
                w1.setName(w.getName());
                w1.setActive(true);
                wService.save(w1);
                return ResponseEntity.ok(new FeedbackToFrontend(true));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }

    }

    //jo
    @PostMapping("/emailNews/signOff")
    public ResponseEntity<?> signOff(@RequestParam String email) {
        try {
//String email=jobj.get("email").toString();
            System.out.println(email);

            WantEmailNews w1 = wService.wantEmailNewsByEmail(email);
            w1.setActive(false);
            wService.save(w1);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

}
