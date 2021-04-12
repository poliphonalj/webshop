package webshop.Model.UsersandRole;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<MyUser> myUserList;


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

    public List<MyUser>getMyUserList() {
        return myUserList;
    }

    public void setMyUserList(List<MyUser >myUserList) {
        this.myUserList = myUserList;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
