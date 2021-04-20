package webshop.Model;

import javax.persistence.*;

//this class provides a random slogan underneath the logo

@Entity
@Table(name="slogans")
public class Slogan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String text;
    private boolean isActive=true;

    public Slogan() {
    }

    public Slogan(String text){
        this.text=text;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
