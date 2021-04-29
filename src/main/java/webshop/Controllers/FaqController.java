package webshop.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webshop.Model.Faq;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.UsersandRole.MyUser;
import webshop.Services.FaqService;

import java.util.HashMap;
import java.util.List;

@RestController
public class FaqController {
//egy obj legyen a kerdes es a valasz
private FaqService faqService;

    @Autowired
    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping("/faq/get")
    public ResponseEntity<?> getFaqs() {
        List<Faq> faqList = faqService.getFaqs();
        if (!(faqList.isEmpty())) {
            HashMap<String,List<Faq>> hMap = new HashMap<>();
            hMap.put("list", faqList);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }
}
