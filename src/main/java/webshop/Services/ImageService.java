package webshop.Services;

import org.springframework.stereotype.Service;
import webshop.Model.Product.Image;
import webshop.Repository.ImageRepo;

@Service
public class ImageService {

    private final ImageRepo imageRepo;

    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public void addImage(Image image){
        Image i=new Image();
        i.setSize(image.getSize());
        i.setTooltip(image.getTooltip());
        i.setFileName(image.getFileName());
       // i.setProductID(image.getProductID());
        imageRepo.saveAndFlush(i);
    }
}
