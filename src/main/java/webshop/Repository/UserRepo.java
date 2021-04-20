package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.UsersandRole.MyUser;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<MyUser, Integer> {
    MyUser findUserByFirstName(String name);
    MyUser findUserByID(long ID);
    MyUser findUserByUsername(String username);
   // MyUser findUserByResetPasswordToken(String resetPasswordToken);
    List<MyUser>findAllByIsActiveTrue();
    //List<MyUser>findAllByIsActiveTrueAndOrderByNumberOfPurchaseDesc();
    List<MyUser>findAll();

}
