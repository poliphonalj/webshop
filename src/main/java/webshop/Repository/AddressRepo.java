package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.Model.UsersandRole.Address;
import webshop.Model.UsersandRole.AddressType;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address, Long> {
    Address findAddressByID(long ID);
    List<Address> findAddressByMyUserID(long myUserID);
  // Address findBydMyUserIDAndAddressType(long myUserID, AddressType addressType);

}
