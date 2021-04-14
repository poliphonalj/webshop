//this class is valid for adding new product with image and category to the sys.


package webshop.DTOs;

import webshop.Model.Product.*;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Locale;

public class NewProductDTO {

//product
    private long ID;
    private String productName;
    private String productDescription;
    private long price;
    private Locale locale;
    private boolean isInPromotion;
    private boolean isOutOfStock;
    private boolean isOutOfSeason;
   // private boolean isActive;
    private Unit unit;
    private long promotedPrice;
    private String promotionDescription;
    private List<Image> imageList;
    private List<Product_Translation> tranlationsList;
    private Category category;

//image
    private String imageName;
    private String type;
    private String imageDescription;
    private String tooltip;
    private long productID;
    private byte[] byteFlow;
    //private boolean isActive=true;
   // private Product product;


}
