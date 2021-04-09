package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.Model.UsersandRole.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
    Address findAddressByID(long ID);

}
