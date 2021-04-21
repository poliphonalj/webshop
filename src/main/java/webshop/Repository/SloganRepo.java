package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.Model.Slogan;
import webshop.Model.UsersandRole.Role;

public interface SloganRepo  extends JpaRepository<Slogan, Long>{
        Slogan findSloganByID(long ID);
    }
