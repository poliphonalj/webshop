package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Partner;
import webshop.Services.PartnerService;

import java.util.HashMap;
import java.util.List;

@RestController
public class PartnerController {
    PartnerService pService;

    @Autowired
    public PartnerController(PartnerService pService) {
        this.pService = pService;
    }

    @PutMapping("partners/save")
    public ResponseEntity<?> save(@RequestBody Partner p) {
        long ID = pService.newPartner(p);
        if (ID >= 0) {
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } else {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @DeleteMapping("/partners/remove/({ID})")
    public ResponseEntity<?> remove (@PathVariable long ID) {
        long a = pService.removePartner(ID);
        if (a >= 0) {
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } else {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PutMapping("/partners/reAdd/({ID})")
    public ResponseEntity<?> reAdd (@PathVariable long ID) {
        long a = pService.reAddPartner(ID);
        if (a >= 0) {
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } else {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }




    @GetMapping("partners/get")
    public ResponseEntity<?> getPartners() {
        List<Partner> list= pService.getPartners();
        if (list.size()!=0) {
            HashMap<String, List<Partner>>hmap=new HashMap<>();
            hmap.put("list",list);
            return ResponseEntity.ok(hmap);
        } else {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @GetMapping("partners/getUs")
    public ResponseEntity<?> getUsPartners() {
        List<Partner> list= pService.getUsPartners();
        if (list.size()!=0) {
            HashMap<String, List<Partner>>hmap=new HashMap<>();
            hmap.put("list",list);
            return ResponseEntity.ok(hmap);
        } else {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

}
