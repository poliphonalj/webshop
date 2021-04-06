package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Product.Product;
import webshop.Model.UsersandRole.MyUser;
import webshop.Services.UserService;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }


    @PostMapping("/user/new")
    public ResponseEntity<?>addUser(@RequestBody MyUser myUser){
        try {
            userService.addUser(myUser.getFirstName(), myUser.getLastName(), myUser.getEmail(), myUser.getPhoneNumber(), myUser.getPassword());
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PostMapping("/user/remove/{ID}")
    public ResponseEntity<?>removeUser(@RequestBody long ID){
        try {
            userService.removeUser(ID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @GetMapping("/user/list/actives")
    public ResponseEntity<?> listActiveUsers(){
        List<MyUser> list = userService.listActiveUsers();
        if (!(list.isEmpty())) {
            HashMap<String, List<MyUser>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }


    @GetMapping("/user/list/all")
    public ResponseEntity<?> listAllUsers(){
        List<MyUser> list = userService.listAllUsers();
        if (!(list.isEmpty())) {
            HashMap<String, List<MyUser>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }



}
