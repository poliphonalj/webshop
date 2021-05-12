package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webshop.Model.AboutUs;
import webshop.Model.Faq;
import webshop.Model.FeedbackToFrontend;
import webshop.Services.AboutUsService;

import java.util.HashMap;
import java.util.List;

@RestController
public class AboutUsController {
    private AboutUsService aboutUsService;

    @Autowired
    public AboutUsController(AboutUsService aboutUsService) {
        this.aboutUsService = aboutUsService;
    }

    @GetMapping("/aboutUs")
    public ResponseEntity<?>getAboutUs(){
        AboutUs a=aboutUsService.getAboutUs();
        System.out.println(a);
            return ResponseEntity.ok().body(a);
    }
}
