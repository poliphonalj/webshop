package webshop.Controllers;

//TODO vásárlásnál novelni a number of purchase erteket


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.utility.RandomString;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import webshop.DTOs.*;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Product.Product;
import webshop.Model.UsersandRole.MyUser;
import webshop.Services.AddressService;
import webshop.Services.EmailService;
import webshop.Services.MyUserDetailsService;
import webshop.Utils.JwtTokenUtil;

import java.util.HashMap;
import java.util.List;


@RestController
public class UserController {

    static Logger l = LoggerFactory.getLogger(UserController.class);
    MyUserDetailsService myUserDetailsService;
    AuthenticationManager authenticationManager;
    AddressService addressService;
    EmailService emailService;
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserController(MyUserDetailsService myUserDetailsService, AuthenticationManager authenticationManager,
                          AddressService addressService, EmailService emailService, JwtTokenUtil jwtTokenUtil) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.addressService = addressService;
        this.emailService = emailService;
        this.jwtTokenUtil=jwtTokenUtil;
    }


    ///This method deals with the user authentication.
    ///It is also responsible for setting the lastTimeLoggedIn field.

    //ok-csak az aktivak lephetnek be
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            String u = authenticationRequestDTO.getUsername();
            if (myUserDetailsService.isStillActive(u)) {
                Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(),
                        authenticationRequestDTO.getPassword()));

                UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
                String username = userDetails.getUsername();
                MyUser myUser = myUserDetailsService.loadUserByUsername(username);

                final String token = jwtTokenUtil.generateToken(userDetails);

                JSONObject jObj = myUserDetailsService.returnForSuccedLogin(myUser.getFirstName(),
                        ((List) (authenticate.getAuthorities())).get(0).toString(),
                        username, myUser.getUserID(),token);

                return ResponseEntity.ok().body(jObj);
            } else {
                return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //ok-nezi hogy van e mar letezo user ilyen usernevevn
    //ha nincs kitoltve a delivery vagy billing addressnel egy sor
    //azaz a field erteke null, akkor automatikusan a home address erteket adja nekik
    @PostMapping("/user/new")
    public ResponseEntity<?> addUser(@RequestBody NewUserDTO newUserDTO) {
        try {
            myUserDetailsService.addUser(newUserDTO);
            emailService.successfulRegistration(newUserDTO.getFirstName(), newUserDTO.getUsername());
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            l.error("kisnyul", e);
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    //ok
    @PostMapping("/user/remove/{ID}")
    public ResponseEntity<?> removeUser(@PathVariable long ID) {
        try {
            myUserDetailsService.removeUser(ID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    //ok
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


//1 kap egy emilt a token vegu linkkel-ok
    //2.rabok eljut egy token vegu cimre
    //itt a tokent at kell adni a  znek
    //igy o geberál egy jelszo modosito oldalt
    //ezen kitoltes utan visszajn a reste pw re egy request, benne a token es a pass es en azonositas utan atirom a jelszot ok

    //generates a token and send it in an email as a link
    @PostMapping("/user/forgot_password")
    public ResponseEntity<?> processForgotPassword(@RequestBody JSONObject jObj) {///userID van a reqben csak
        long userID = Long.parseLong(jObj.get("userID").toString());
        String token = RandomString.make(30);
        System.out.println(token);
        try {
            myUserDetailsService.updateResetPasswordToken(token, userID);//saves the token into the db

            String resetPasswordLink = "http://localhost:8080/user/reset_password?token=" + token;//generates a link and send it in email
            emailService.forgotPassword(resetPasswordLink, userID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //get the password and checks it and saves it to the db
    @PostMapping("/user/reset_password")
    public void processResetPassword(@RequestBody JSONObject jObj) {

        String token = jObj.get("token").toString();
        String password = jObj.get("password").toString();
        MyUser myUser = myUserDetailsService.getByResetPasswordToken(token);

        if (myUser == null) {
            System.out.println("invalid token");

        } else {
            myUserDetailsService.updatePassword(myUser, password);
            System.out.println("You have successfully changed your password.");
        }
    }


    //ok
    @PostMapping("/user/modify/phonenumber")
    public ResponseEntity<?> changePhoneNumber(@RequestBody NewPhoneNumberDTO newPhoneNumberDTO) {
        try {
            myUserDetailsService.changePhonenumber(newPhoneNumberDTO);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


    //ok
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

    //????
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

    //ok
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


   // user/getfull/id  full usert visszaad kiv a jelszot listaban kivenni a jelszot



    //ok
    //egy stringet adok vissza, abbol epitem fel a jsond direktben
    @GetMapping(value = "/user/get/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByID(@PathVariable long ID) {
        try {
            String r = myUserDetailsService.getUserByID(ID);
            return ResponseEntity.ok().body(r);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //ok
    @PostMapping("/user/rate")
    public ResponseEntity<?> rateTheUser(@RequestBody UserRatingDTO userRatingDTO) {
        try {
            myUserDetailsService.rateTheUser(userRatingDTO);
            return ResponseEntity.ok().body(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //ok
    @GetMapping("/user/getRate/{userID}")
    public ResponseEntity<?> getRateOfUser(@PathVariable long userID) {
        try {
            String rate = myUserDetailsService.getRateOfUser(userID);
            return ResponseEntity.ok().body(rate);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    //itt ha kitroli a szallitasi cimet akkor megkapja automatikusan a home ot mint a regisztracional?igennnn
    @PostMapping("/user/modifyUser/{IDD}")
    public ResponseEntity<?> modify(@RequestBody NewUserDTO myUser, @PathVariable Long IDD) {
        try {
            long Id = IDD;
            myUserDetailsService.modifyUser(myUser, Id);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }


}
