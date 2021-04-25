package webshop.Controllers;

//4.20.1134
//kepes dto
//email

//TODO vásárlásnál novelni a number of purchase erteket


import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import webshop.DTOs.AuthenticationRequestDTO;
import webshop.DTOs.NewPasswordDTO;
import webshop.DTOs.NewPhoneNumberDTO;
import webshop.DTOs.NewUserDTO;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.UsersandRole.MyUser;
import webshop.Services.AddressService;
import webshop.Services.EmailService;
import webshop.Services.MyUserDetailsService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;


@RestController
public class UserController {

    static Logger l = LoggerFactory.getLogger(UserController.class);
    MyUserDetailsService myUserDetailsService;
    AuthenticationManager authenticationManager;
    AddressService addressService;
    EmailService emailService;


    @Autowired
    public UserController(MyUserDetailsService myUserDetailsService, AuthenticationManager authenticationManager,
                          AddressService addressService, EmailService emailService) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.addressService = addressService;
        this.emailService = emailService;
    }


    ///This method deals with the user authentication.
    ///It is also responsible for setting the lastTimeLoggedIn field.

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(),
                    authenticationRequestDTO.getPassword()));
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            String username = userDetails.getUsername();
            MyUser myUser = myUserDetailsService.loadUserByUsername(username);
            JSONObject jObj = myUserDetailsService.returnForSuccedLogin(myUser.getFirstName(),
                    ((List) (authenticate.getAuthorities())).get(0).toString(),
                    username, myUser.getID());

            return ResponseEntity.ok().body(jObj);
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> addUser(@RequestBody NewUserDTO newUserDTO) {
        try {
            myUserDetailsService.addUser(newUserDTO);
            //emailService.successfulRegistration(newUserDTO.getFirstName(), newUserDTO.getUsername());
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            l.error("kisnyul", e);
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PostMapping("/user/remove/{ID}")
    public ResponseEntity<?> removeUser(@RequestBody long ID) {
        try {
            myUserDetailsService.removeUser(ID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PostMapping("/user/modify/password")
    public ResponseEntity<?> changePassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        try {
            myUserDetailsService.changePassword(newPasswordDTO);
            //emailService.newPassword(newPasswordDTO.getPassword(), newPasswordDTO.getUserID());
            //vvvvvvvvvvvvvvvemilt kikuldeni!!!!!

            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PostMapping("/user/forgetpassword")
    public ResponseEntity<?> forgetPassword(@RequestBody MyUser myUser) {
        try {
            //emailService.forgotPassword(myUser);
            //vvvvvvvvvvvvvvvemilt kikuldeni!!!!!
            //forgetpassword

            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    @PostMapping("/user/modify/phonenumber")
    public ResponseEntity<?> changePhoneNumber(@RequestBody NewPhoneNumberDTO newPhoneNumberDTO) {
        try {

            myUserDetailsService.changePhonenumber(newPhoneNumberDTO);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    @GetMapping("/user/list/actives")
    public ResponseEntity<?> listActiveUsers() {
        List<MyUser> list = myUserDetailsService.listActiveUsers();
        if (!(list.isEmpty())) {
            HashMap<String, List<MyUser>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }

    @GetMapping("/user/list/actives/orderedByPurchase")
    public ResponseEntity<?> listActiveUsersInOrder() {
        List<MyUser> list = myUserDetailsService.listActiveUsersInOrder();
        if (!(list.isEmpty())) {
            HashMap<String, List<MyUser>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }


    @GetMapping("/user/list/all")
    public ResponseEntity<?> listAllUsers() {
        List<MyUser> list = myUserDetailsService.listAllUsers();
        if (!(list.isEmpty())) {
            HashMap<String, List<MyUser>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }

    @GetMapping("/user/get/{ID}")
    public ResponseEntity<?> getUserByID(@PathVariable long ID) {

        try {
            MyUser m = myUserDetailsService.getUserByID(ID);
            return ResponseEntity.ok().body(m);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }
}
