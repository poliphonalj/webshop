package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.UsersandRole.Address;
import webshop.Services.AddressService;

import java.io.IOException;

@RestController
public class AddressController {

    AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address/add")
    public ResponseEntity<?> addAddress(@RequestParam Address address, long userID) throws IOException {
        try{
            addressService.addAddress(address, userID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


}
