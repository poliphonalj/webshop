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
import webshop.DTOs.LoggedInUserDTO;
import webshop.DTOs.NewUserDTO;
import webshop.Model.UsersandRole.Address;
import webshop.Model.UsersandRole.MyUser;
import webshop.Model.UsersandRole.Role;
import webshop.Repository.AddressRepo;
import webshop.Repository.RoleRepo;
import webshop.Repository.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public void addUser(NewUserDTO newUserDTO) {
        MyUser u = new MyUser();
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
        a.setPostCode(newUserDTO.getPostCode());
        a.setSimpleAddress(newUserDTO.getSimpleAddress());
        a.setComment(newUserDTO.getComment());

        addressRepo.saveAndFlush(a);

    }

    public void removeUser(long ID) {
        MyUser myUser = userRepo.findUserByID(ID);
        myUser.setActive(false);
        userRepo.saveAndFlush(myUser);
    }

    public void changePassword(String newPassword, MyUser myUser) {
        myUser.setPassword(newPassword);
        userRepo.saveAndFlush(myUser);
    }

    public void changePhonenumber(String newPhoneNumber, MyUser myUser) {
        myUser.setPhoneNumber(newPhoneNumber);
        userRepo.saveAndFlush(myUser);
    }

    public List<MyUser> listActiveUsers() {
        return userRepo.findAllByIsActiveTrue();
    }

    public List<MyUser> listAllUsers() {
        List<MyUser> list = userRepo.findAll();
        return userRepo.findAll();
    }

    public JSONObject returnForSuccedLogin(String firstName, String role, String username) {
        MyUser u = loadUserByUsername(username);
        u.setLastLoggedInAt(LocalDateTime.now());
        userRepo.saveAndFlush(u);

        LoggedInUserDTO l = new LoggedInUserDTO();
        l.setFirstName(firstName);
        l.setRole(role);
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

