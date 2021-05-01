package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.UsersandRole.Role;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findRoleByRoleName(String userName);
    List<Role> findAll();
}
