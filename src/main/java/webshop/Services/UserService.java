package webshop.Services;

// TODO
// roles
//edit function
//forgotten password
//different types of addresses


import org.springframework.stereotype.Service;
import webshop.Model.UsersandRole.MyUser;
import webshop.Repository.UserRepo;

import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(String firstName, String lastName, String email, int phoneNumber, String password) {
        MyUser u = new MyUser();
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        u.setPhoneNumber(phoneNumber);
        u.setPassword(password);
        u.setActive(true);

        userRepo.saveAndFlush(u);
    }

    public void removeUser(long ID) {
        MyUser myUser=userRepo.findUserByID(ID);
        myUser.setActive(false);
        userRepo.saveAndFlush(myUser);
    }

    public List<MyUser> listActiveUsers(){
       return userRepo.findAllByIsActiveTrue();
    }

    public List<MyUser> listAllUsers(){
        return userRepo.findAll();
    }
}
