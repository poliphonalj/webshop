package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.Slogan;
import webshop.Model.UsersandRole.Role;

import java.util.List;
@Repository
public interface SloganRepo extends JpaRepository<Slogan, Long> {
   // Slogan findSloganByIDAndIsActiveTrue(long ID);
    List<Slogan> findSlogansByActiveIsTrue();
    Slogan findSloganByID(long ID);
    List<Slogan> findAll();
}
