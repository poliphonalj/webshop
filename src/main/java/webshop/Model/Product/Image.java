package webshop.Model.Product;

import javax.persistence.*;

@Entity
@Table(name="images")

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String name;
    private String type;
    private String description;
    private String tooltip;
    private long productID;

    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    @Column(name = "byteFlow", length = 100000)

    private byte[] byteFlow;
    private boolean isActive=true;

    @ManyToOne
    private Product product;


    public Image() {
    }

    public Image(String name, String type, byte[] byteFlow, String description, String tooltip ) {
        this.name = name;
        this.type = type;
        this.description=description;
        this.tooltip=tooltip;
        this.byteFlow = byteFlow;

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getByteFlow() {
        return byteFlow;
    }

    public void setByteFlow(byte[] byteFlow) {
        this.byteFlow = byteFlow;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }
}
