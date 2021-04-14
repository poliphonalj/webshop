package webshop.Controllers;

//TODO method for return


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
import org.springframework.web.bind.annotation.*;
import webshop.DTOs.AuthenticationRequestDTO;
import webshop.DTOs.NewUserDTO;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.UsersandRole.MyUser;
import webshop.Services.AddressService;
import webshop.Services.EmailService;
import webshop.Services.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
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


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(HttpServletResponse response,
                                                       @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            // Authenti7cation authenticate= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            //        , authenticationRequestDTO.getPassword()));

            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(),
                    authenticationRequestDTO.getPassword()));

            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            String username = userDetails.getUsername();
            MyUser myUser=myUserDetailsService.loadUserByUsername(username);

            System.out.println(authenticate.getAuthorities());
            String role=((List)(authenticate.getAuthorities())).get(0).toString();// itt irja ki a rolet

            String firstName=myUser.getFirstName();//itt anevet

            HashMap<String, String> map = new HashMap<>();
            map.put("successful", "true");
            map.put("firstName", firstName);
            map.put("role", role);

            return ResponseEntity.ok().body(map);

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> addUser(@RequestBody NewUserDTO newUserDTO) {
        try {
            myUserDetailsService.addUser(newUserDTO);
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
    public ResponseEntity<?> changePassword(@RequestBody String password, MyUser myUser) {
        try {
            myUserDetailsService.changePassword(password, myUser);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PostMapping("/user/modify/phonenumber")
    public ResponseEntity<?> changePhoneNumber(@RequestBody String phoneNumber, MyUser myUser) {
        try {
            myUserDetailsService.changePassword(phoneNumber, myUser);
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
}
