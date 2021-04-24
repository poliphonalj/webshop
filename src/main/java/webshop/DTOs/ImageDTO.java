package webshop.DTOs;

import webshop.Model.Product.Image;

public class ImageDTO {
    private long ID;
    private Image image;

    public ImageDTO() {
    }

    public ImageDTO(long ID, Image image) {
        this.ID = ID;
        this.image = image;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
