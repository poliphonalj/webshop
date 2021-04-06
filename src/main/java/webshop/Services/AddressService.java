package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.Model.UsersandRole.Address;
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

    public void addAddress(Address address, long ID){
        Address a=new Address();
        a.setPostCode(address.getPostCode());
        a.setSimpleAddress(address.getSimpleAddress());
        a.setComment(address.getComment());
        a.setAddressType(address.getAddressType());
        a.setMyUser(userRepo.findUserByID(ID));

        MyUser myUser=userRepo.findUserByID(ID);
        List<Address> list=myUser.getMyAddressList();
        list.add(a);
        myUser.setMyAddressList(list);

        userRepo.saveAndFlush(myUser);
        addressRepo.saveAndFlush(a);
    }
}
