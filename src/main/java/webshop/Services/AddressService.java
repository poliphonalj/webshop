package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.DTOs.NewAddressDTO;
import webshop.Model.UsersandRole.Address;
import webshop.Model.UsersandRole.AddressType;
import webshop.Model.UsersandRole.MyUser;
import webshop.Repository.AddressRepo;
import webshop.Repository.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    AddressRepo addressRepo;
    UserRepo userRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo, UserRepo userRepo) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }

    public AddressService() {
    }

    //Ok
    //fekuk ir egy cimet
    //a request addressType mezojetol fuggoen ami az enumok erteket veszi fel
    public void addAddress(NewAddressDTO newAddressDTO) {
        List<Address> addressList = addressRepo.findAddressByMyUserID(newAddressDTO.getUserID());
        Address a=null;
        if (newAddressDTO.getAddressType() == AddressType.HOME_ADDRESS) {
            a = addressList.stream().
                    filter(address -> address.getAddressType() == AddressType.HOME_ADDRESS).
                    collect(Collectors.toList()).get(0);
        }

        else if(newAddressDTO.getAddressType() == AddressType.DELIVERY_ADDRESS){
            a = addressList.stream().
                    filter(address -> address.getAddressType() == AddressType.DELIVERY_ADDRESS).
                    collect(Collectors.toList()).get(0);
        }
        else if(newAddressDTO.getAddressType() == AddressType.BILLING_ADDRESS){
            a = addressList.stream().
                    filter(address -> address.getAddressType() == AddressType.BILLING_ADDRESS).
                    collect(Collectors.toList()).get(0);
        }
        a.setPostCode(newAddressDTO.getPostCode());
        a.setCity(newAddressDTO.getCity());
        a.setSimpleAddress(newAddressDTO.getSimpleAddress());
        a.setComment(newAddressDTO.getComment());
        a.setAddressType(newAddressDTO.getAddressType());
        a.setMyUser(userRepo.findUserByID(newAddressDTO.getUserID()));
        addressRepo.saveAndFlush(a);
    }

    public AddressType[] listTheTypes() {
        return AddressType.values();
    }

    public Address getDeliveryAddressByUserID(long userID) {
        List<Address> addressList = addressRepo.findAddressByMyUserID(userID);
        Address a = addressList.stream().
                filter(address -> address.getAddressType() == AddressType.DELIVERY_ADDRESS).
                collect(Collectors.toList()).
                get(0);

        return a;
    }
}
