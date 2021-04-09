package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.UsersandRole.Address;
import webshop.Model.UsersandRole.AddressType;
import webshop.Model.UsersandRole.MyUser;
import webshop.Repository.AddressRepo;
import webshop.Repository.UserRepo;

import java.util.List;

@Service
public class AddressService {
    AddressRepo addressRepo;
    UserRepo userRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo,UserRepo userRepo) {
        this.addressRepo = addressRepo;
        this.userRepo=userRepo;
    }

    public void addAddress( Address address){
        Address a=new Address();
        a.setPostCode(address.getPostCode());
        a.setSimpleAddress(address.getSimpleAddress());
        a.setComment(address.getComment());
        a.setAddressType(address.getAddressType());
        a.setMyUser(userRepo.findUserByUsername(address.getMyUser().getUsername()));
        addressRepo.saveAndFlush(a);
    }

    public AddressType[] listTheTypes(){
        return AddressType.values();
    }
}
