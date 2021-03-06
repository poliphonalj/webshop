package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.Model.DeliveryDay;
import webshop.Model.DeliveryGaps;
import webshop.Model.FeedbackToFrontend;
import webshop.Services.DeliveryService;

import java.time.LocalDateTime;
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

    //egyzer az elejen
    @GetMapping("/delivery/start")
    public ResponseEntity<?> start() {
        try {
            deliveryService.setUp();///ezt az idozito csinalja igazibol
            //kitorli az elozo delivery db ket es ujjat hoz letre tiszta lappal kezd
            //persze elotte  kmenti egy fajlba az adatokat!!!!
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getCause() + e.fillInStackTrace());
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    //majd ezt kell futtatni
    @GetMapping("/delivery/GetNextPossibles")
    public ResponseEntity<?> getNext2DaysAndPossibleGaps() {
        try {
           List<DeliveryDay>list=deliveryService.getAvailableDeliveries();
           HashMap<String,List<DeliveryDay>>hMap=new HashMap<>();
           hMap.put("list",list);
            return ResponseEntity.ok(hMap);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //majd ezt
    @PostMapping("/delivery/BookADelivery")
    public ResponseEntity<?> saveDeliveryTime(@RequestParam long deliveryDayID, long deliveryGapsID, long orderID) {
        try {
//long deliveryDayID=deliveryDay.getDeliveryDayID();
//long deliveryGapsID=deliveryGaps.getDeliveryGapsID();

            deliveryService.book(deliveryDayID, deliveryGapsID);

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
