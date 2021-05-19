package webshop.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String name;
    private String phone;
    private String link;
    private boolean isActive;
    private String description;
    private boolean us;

    // private Image image;

    public Partner() {
        us = false;
    }

    public Partner(String name, String phone, String link, String description, boolean us) {
        this.name = name;
        this.phone = phone;
        this.link = link;
        this.us = us;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUs() {
        return us;
    }

    public void setUs(boolean us) {
        this.us = us;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
