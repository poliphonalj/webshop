package webshop.Controllers;

//TODO method for return


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import webshop.DTOs.AuthenticationRequestDTO;
import webshop.DTOs.NewUserDTO;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.UsersandRole.MyUser;
import webshop.Services.AddressService;
import webshop.Services.EmailService;
import webshop.Services.MyUserDetailsService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
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
        this.emailService=emailService;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(HttpServletResponse response,
                                                       @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            // Authentication authenticate= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            //        , authenticationRequestDTO.getPassword()));

            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(),
                    authenticationRequestDTO.getPassword()));
            //if (authenticate.isAuthenticated()) {
            //  SecurityContextHolder.getContext().setAuthentication(authenticate);
            //return ResponseEntity.ok(new FeedbackToFrontend(true));
            //}
            SecurityContextHolder.getContext().getAuthentication().getPrincipal();//automatikusan csinalja
            System.out.println(authenticate.getAuthorities());
            System.out.println(authenticate.toString());
            return ResponseEntity.ok(new FeedbackToFrontend(true));

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> addUser(@RequestBody NewUserDTO newUserDTO) {
        try {
         // if(myUserDetailsService.loadUserByUsername(newUserDTO.getUsername())==null){
              myUserDetailsService.addUser(newUserDTO);
              emailService.successfulRegistration(newUserDTO.getFirstName());
              return ResponseEntity.ok(new FeedbackToFrontend(true));
       //   }
          //throw new UserExistException();
        } catch (Exception e) {
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
