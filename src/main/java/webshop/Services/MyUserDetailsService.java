package webshop.Services;

// TODO
// roles
//edit function
//forgotten password
//different types of addresses
//change password
//change phonenumber
//change address


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.UsersandRole.MyUser;
import webshop.Repository.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @PersistenceContext
    EntityManager em;

    private UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(MyUser myUser) {
        MyUser u = new MyUser();
        u.setFirstName(myUser.getFirstName());
        u.setLastName(myUser.getLastName());
        u.setUsername(myUser.getUsername());
        u.setPhoneNumber(myUser.getPhoneNumber());
        u.setPassword(myUser.getPassword());
        u.setActive(true);

        userRepo.saveAndFlush(u);
    }

    public void removeUser(long ID) {
        MyUser myUser=userRepo.findUserByID(ID);
        myUser.setActive(false);
        userRepo.saveAndFlush(myUser);
    }

    public void changePassword(String newPassword, MyUser myUser){
        myUser.setPassword(newPassword);
        userRepo.saveAndFlush(myUser);
    }

    public void changePhonenumber(String newPhoneNumber, MyUser myUser){
        myUser.setPhoneNumber(newPhoneNumber);
        userRepo.saveAndFlush(myUser);
    }

    public List<MyUser> listActiveUsers(){
       return userRepo.findAllByIsActiveTrue();
    }

    public List<MyUser> listAllUsers(){
        return userRepo.findAll();
    }

    @Transactional
    @Override
    public MyUser loadUserByUsername(String username) throws UsernameNotFoundException {
            MyUser u = (MyUser) em.createQuery("SELECT users FROM MyUser users where users.username =:p")
                    .setParameter("p", username).getSingleResult();
            return u;//DTOZNI
        }
    }

