package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/emailNews/sendANew")
    public ResponseEntity<?> sendEmailNews(@RequestBody String text) {
        try {
            List<WantEmailNews> list = wService.getAllWhoWantsEmailNews();
            emailService.sendNews(text, list);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @GetMapping("/emailNews/allActives")
    public ResponseEntity<?> getAllActives() {
        try {
            List<WantEmailNews> list = wService.getAllWhoWantsEmailNews();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    //a regisztracio soran is be tud jelentkezni a mostani mukodes alapjan
    @PostMapping("/emailNews/signUp")
    public ResponseEntity<?> signUp(@RequestBody String email, String name) {
        try {
            if (wService.wantEmailNewsByEmail(email) != null) {
                wService.reactivate(email);
                return ResponseEntity.ok(new FeedbackToFrontend(true));
            } else {
                WantEmailNews w = new WantEmailNews();
                w.setEmail(email);
                w.setName(name);
                w.setActive(true);
                wService.save(w);
                return ResponseEntity.ok(new FeedbackToFrontend(true));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }

    }

    @PostMapping("/emailNews/signOff")
    public ResponseEntity<?> signOff(@RequestBody String email) {
        try {
            WantEmailNews w = wService.wantEmailNewsByEmail(email);
            w.setActive(false);
            wService.save(w);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

}
