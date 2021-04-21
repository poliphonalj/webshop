package webshop.Services;

// TODO
//beegetve a user role
// roles
//edit function
//forgotten password
//different types of addresses
//change password
//change phonenumber
//change address


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.DTOs.*;
import webshop.Exceptions.UserExistException;
import webshop.Model.UsersandRole.Address;
import webshop.Model.UsersandRole.AddressType;
import webshop.Model.UsersandRole.MyUser;
import webshop.Model.UsersandRole.Role;
import webshop.Repository.AddressRepo;
import webshop.Repository.RoleRepo;
import webshop.Repository.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @PersistenceContext
    EntityManager em;

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private AddressRepo addressRepo;

    public MyUserDetailsService(UserRepo userRepo, RoleRepo roleRepo, AddressRepo addressRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.addressRepo = addressRepo;
    }

    public void addUser(NewUserDTO newUserDTO) throws UserExistException {
        MyUser u = new MyUser();

        if (userRepo.findUserByUsername(newUserDTO.getUsername()) == null) {
            System.out.println("nincs meg ilyen user");
            Role r = roleRepo.findRoleByRoleName("user");

            u.setFirstName(newUserDTO.getFirstName());
            u.setLastName(newUserDTO.getLastName());
            u.setUsername(newUserDTO.getUsername());
            u.setPhoneNumber(newUserDTO.getPhoneNumber());
            u.setPassword(newUserDTO.getPassword());
            List<Role> list = new ArrayList<>();
            list.add(r);
            u.setRoleList(list);
            u.setActive(true);


            userRepo.saveAndFlush(u);

            Address a = new Address();
            a.setMyUser(userRepo.findUserByID(u.getID()));
            a.setPostCode(newUserDTO.getPostCode_home());
            a.setSimpleAddress(newUserDTO.getAddress_home());
            a.setComment(newUserDTO.getComment_home());
            a.setAddressType(AddressType.HOME_ADDRESS);

            Address a2 = new Address();
            a2.setMyUser(userRepo.findUserByID(u.getID()));
            a2.setPostCode(newUserDTO.getPostCode_delivery());
            a2.setSimpleAddress(newUserDTO.getAddress_delivery());
            a2.setComment(newUserDTO.getComment_delivery());
            a2.setAddressType(AddressType.DELIVERY_ADDRESS);

            Address a3 = new Address();
            a3.setMyUser(userRepo.findUserByID(u.getID()));
            a3.setPostCode(newUserDTO.getPostCode_billing());
            a3.setSimpleAddress(newUserDTO.getAddress_billing());
            a3.setComment(newUserDTO.getComment_billing());
            a3.setAddressType(AddressType.BILLING_ADDRESS);

            addressRepo.saveAndFlush(a);
            addressRepo.saveAndFlush(a2);
            addressRepo.saveAndFlush(a3);

        }
        else {
            System.out.println("mar van ilyen user");
            throw new UserExistException();
        }
    }

    public void removeUser(long ID) {
        MyUser myUser = userRepo.findUserByID(ID);
        myUser.setActive(false);
        userRepo.saveAndFlush(myUser);
    }

    public void changePassword(NewPasswordDTO newPasswordDTO) {
        MyUser myUser = userRepo.findUserByID(newPasswordDTO.getUserID());
        myUser.setPassword(newPasswordDTO.getPassword());
        userRepo.saveAndFlush(myUser);
    }

    public void changePhonenumber(NewPhoneNumberDTO newPhoneNumberDTO) {
        MyUser myUser = userRepo.findUserByID(newPhoneNumberDTO.getUserID());
        myUser.setPhoneNumber(newPhoneNumberDTO.getPhoneNumber());
        userRepo.saveAndFlush(myUser);
    }


    public List<MyUser> listActiveUsers() {
        return userRepo.findAllByIsActiveTrue();
    }

    public List<MyUser> listActiveUsersInOrder() {
        //return userRepo.findAllByIsActiveTrueAndOrderByNumberOfPurchaseDesc();
        return null;
    }

    public List<MyUser> listAllUsers() {
        List<MyUser> list = userRepo.findAll();
        return userRepo.findAll();
    }

    public JSONObject getUserByID(long ID) {
        JSONObject jsonObj = new JSONObject();
        MyUser m = userRepo.findUserByID(ID);
        List<Address> list = addressRepo.findAddressByMyUserID(ID);

        Address a1 = list.stream().filter(a -> a.getAddressType() == AddressType.HOME_ADDRESS).collect(Collectors.toList()).get(0);
        Address a2 = list.stream().filter(b -> b.getAddressType() == AddressType.DELIVERY_ADDRESS).collect(Collectors.toList()).get(0);
        Address a3 = list.stream().filter(c -> c.getAddressType() == AddressType.BILLING_ADDRESS).collect(Collectors.toList()).get(0);

        //Address a2=addressRepo.findByAddressByTypeAndMyUserID(AddressType.DELIVERY_ADDRESS, ID);
        // Address a3=addressRepo.findByAddressByTypeAndMyUserID(AddressType.BILLING_ADDRESS, ID);

        ReturnUserDTO r = new ReturnUserDTO();
        r.setActive(m.isActive());
        r.setID(m.getID());
        r.setFirstName(m.getFirstName());
        r.setLastName(m.getLastName());
        r.setUsername(m.getUsername());
        r.setRole(m.getRoleList().get(0));
        r.setLastLoggedInAt(m.getLastLoggedInAt());
        r.setLastPurchasedAt(m.getLastPurchasedAt());
        r.setLocale(m.getLocale());
        r.setPhoneNumber(m.getPhoneNumber());


        jsonObj.put("user", r);
        jsonObj.put("homeAddress", a1);
        jsonObj.put("deliveryAddress", a2);
        jsonObj.put("billingAddress", a3);

//jsonObj.
        //r.setMyAddressList(a.getMyUser().getMyAddressList());
        return jsonObj;
    }

    public void rateTheUser(UserRatingDTO u) {
        MyUser myUser = userRepo.findUserByID(u.getUserID());
        myUser.setUserRates(u.getRate());
        userRepo.saveAndFlush(myUser);
    }

    public String getRateOfUser(long userID) {
        MyUser myUser = userRepo.findUserByID(userID);
        return myUser.getUserRates();
    }



    public void updateResetPasswordToken(String token, long userID) throws Exception {
        MyUser myUser = userRepo.findUserByID(userID);
        if (myUser != null) {
            myUser.setResetPasswordToken(token);
            userRepo.save(myUser);
        } else {
            System.out.println("hiba");
        }
    }

    public MyUser getByResetPasswordToken(String token) {
        System.out.println("itt vagyok rgyogok");
        return userRepo.findUserByResetPasswordToken(token);
    }

    public void updatePassword(MyUser myUser, String newPassword) {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       // String encodedPassword = passwordEncoder.encode(newPassword);
        myUser.setPassword(newPassword);

        myUser.setResetPasswordToken(null);
        userRepo.save(myUser);
    }













    public JSONObject returnForSuccedLogin(String firstName, String role, String username, long ID) {
        MyUser u = loadUserByUsername(username);
        u.setLastLoggedInAt(LocalDateTime.now());
        userRepo.saveAndFlush(u);

        LoggedInUserDTO l = new LoggedInUserDTO();
        l.setFirstName(firstName);
        l.setRole(role);
        l.setUserID(ID);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("succesful", "true");
        JSONArray list = new JSONArray();
        list.add(l);
        jsonObject.put("list", list);
        return jsonObject;
    }


    @Transactional
    @Override
    public MyUser loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser u = (MyUser) em.createQuery("SELECT users FROM MyUser users where users.username =:p")
                .setParameter("p", username).getSingleResult();
        return u;//DTOZNI
    }
}

