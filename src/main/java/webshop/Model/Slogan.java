package webshop.Model;

import javax.persistence.*;

//this class provides a random slogan underneath the logo

@Entity
@Table(name="slogans")
public class Slogan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String text;

    public Slogan() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
