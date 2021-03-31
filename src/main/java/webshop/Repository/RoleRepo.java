package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.Model.UsersandRole.Role;


public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findRoleByRoleName(String userName);
}
