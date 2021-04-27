package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webshop.Model.DeliveryDate;
import webshop.Model.FeedbackToFrontend;
import webshop.Services.DeliveryService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//returns the next 2 available delivery days

@RestController
public class DeliveryController {
    DeliveryService deliveryService;
    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/delivery/nextTwo")
    public ResponseEntity<?> next2Days( ) {
        try {
           List<DeliveryDate> l= deliveryService.nextTwoDeliveryDays();

           HashMap<String, List<DeliveryDate>>hmap=new HashMap<>();
            hmap.put("list",l);

           return ResponseEntity.ok(hmap);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    @PostMapping("/delivery/setTime")
    public ResponseEntity<?> saveDeliveryTime(@RequestBody LocalDateTime l, long orderID) {
        try {
            //vajon az ordernek van delivery time ja? e akkor mi az az orddertime
            // ezt megkersdezni a lazstol holnap
            //amugy meg egy szervizol kikerni az ordert es beltolteni a delivery timot
            //datum egy idoszak tomb


            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }
}
