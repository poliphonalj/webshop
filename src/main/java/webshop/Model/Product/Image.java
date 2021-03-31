package webshop.Model.Product;

import javax.persistence.*;

@Entity
@Table(name="images")

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String name;
    private String tooltip;
    private long size;
    private char images;//Blob???????????????????????????????????????????InputStream

    @ManyToOne
    private Product product;

    public Image() {
    }

    public Image(String name, String tooltip, long size) {
        this.name = name;
        this.tooltip = tooltip;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
