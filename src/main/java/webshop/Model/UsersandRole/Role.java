package webshop.Model.UsersandRole;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String roleName;
@ManyToMany(mappedBy = "roleList" )
List<MyUser>userList;

    public Role(String role) {
        this.roleName = role;
    }

    public Role() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }



    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
