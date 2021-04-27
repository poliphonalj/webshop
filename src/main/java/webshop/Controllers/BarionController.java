package webshop.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.DTOs.NewUserDTO;
import webshop.Model.Barion.Payment;
import webshop.Model.FeedbackToFrontend;
import webshop.Services.BarionService;

@RestController
public class BarionController {

    private BarionService barionService;

    @Autowired
    public BarionController(BarionService barionService) {
        this.barionService = barionService;
    }

   @PostMapping("/v2/Payment/Start")
    public ResponseEntity<?> barion(@RequestBody Payment p ) {
        try {
            barionService.barionPayment(p);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    @GetMapping("/v2/Payment/GetPaymentState")
    public ResponseEntity<?> barionGetPayment( ) {
        try {
            //barionService.getPaymentStatus();
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }



}
