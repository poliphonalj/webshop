package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import webshop.Model.FeedbackToFrontend;
import webshop.Services.EmailService;

@RestController
public class EmailNewsController {
    @Autowired
    EmailService emailService;

    @GetMapping("/emailNews")
    public ResponseEntity<?> sendEmailNews(@RequestBody String text){
        try{
            emailService.   sendNews(text);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }

    }
}
