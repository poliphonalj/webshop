package webshop.Model.Product;

import javax.persistence.*;

@Entity
@Table(name="images")

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String fileName;
    private String tooltip;
    private long size;


    @ManyToOne
    private Product product;

    public Image() {
    }

    public Image(String fileName, String tooltip, long size) {
        this.fileName = fileName;
        this.tooltip = tooltip;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String name) {
        this.fileName = name;
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
