package webshop.Model;

import javax.persistence.*;

//this class provides a random slogan underneath the logo

@Entity
@Table(name="slogans")
public class Slogan {
    @Id
    @GeneratedValue
    private long ID;
    private String text;
    private boolean active =true;

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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
