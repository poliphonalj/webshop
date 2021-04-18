package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.DTOs.NewAddressDTO;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.UsersandRole.Address;
import webshop.Model.UsersandRole.AddressType;
import webshop.Model.UsersandRole.MyUser;
import webshop.Services.AddressService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class AddressController {

    AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @PostMapping("/address/new")
    public ResponseEntity<?> addAddress(@RequestBody NewAddressDTO newAddressDTO) throws IOException {
        try{

            addressService.addAddress(newAddressDTO);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @GetMapping("/address/types/list")
    public ResponseEntity<?> listActiveUsers() {
       AddressType[] list = addressService.listTheTypes();
        if (list.length!=0) {
            HashMap<String, AddressType[]> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }


}
