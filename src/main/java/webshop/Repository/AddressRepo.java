package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.UsersandRole.Address;
import webshop.Model.UsersandRole.AddressType;

import java.util.List;
@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
    Address findAddressByID(long ID);
    List<Address> findAddressByMyUserUserID(long userID);
  // Address findBydMyUserIDAndAddressType(long myUserID, AddressType addressType);

}
