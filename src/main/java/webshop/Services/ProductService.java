package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.DTOs.EndOfPromotionDTO;
import webshop.DTOs.NewProductPriceDTO;
import webshop.DTOs.ProductsForPromotionDTO;
import webshop.DTOs.PromotedProductDTO;
import webshop.Exceptions.ProductAddedException;
import webshop.Model.Product.Product;
import webshop.Model.Product.Unit;
import webshop.Repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @Transactional
    public void addProduct(String productName, String productDescription, long productPrice, Unit productUnit,
                           Locale productLocale, int productCategoryID, boolean productIsInPromotion,
                           boolean productIsOutOfStock, boolean productIsOutOfSeason
    ) throws ProductAddedException {

        if (productRepo.findProductByName(productName) != null) {
            throw new ProductAddedException();
        } else {
            Product p = new Product();

            p.setName(productName);
            p.setDescription(productDescription);
            p.setPrice(productPrice);
            p.setUnit(productUnit);
            p.setLocale(productLocale);
            p.setCategoryID(productCategoryID);
            p.setInPromotion(productIsInPromotion);
            p.setOutOfStock(productIsOutOfStock);
            p.setOutOfSeason(productIsOutOfSeason);

            productRepo.saveAndFlush(p);
        }
    }

    @Transactional
    public void setPromotion(List<ProductsForPromotionDTO>list) {
        for (int i = 0; i < list.size(); i++) {
            Product p = productRepo.findProductByID(list.get(i).getID());
            p.setPrice(list.get(i).getPromotedPrice());
            p.setPromotionDescription(list.get(i).getPromotionDescription());
            p.setInPromotion(true);
            productRepo.saveAndFlush(p);
        }
    }

    public List<PromotedProductDTO> getPromotedList(){
       List<PromotedProductDTO>listToReturn=new ArrayList<>();
        List<Product> list=productRepo.findByIsInPromotion(true);
        for (Product actualProduct : list) {
            listToReturn.add(new PromotedProductDTO(actualProduct.getID(),actualProduct.getPromotedPrice(),
                    actualProduct.getPrice(),actualProduct.getPromotionDescription()));
        }
        return listToReturn;
    }


    @Transactional
    public void setEndOfPromotion(List<EndOfPromotionDTO>list) {
        for (int i = 0; i < list.size(); i++) {
            Product p = productRepo.findProductByID(list.get(i).getID());
            p.setPrice(list.get(i).getNewPrice());
            p.setInPromotion(false);
            productRepo.saveAndFlush(p);
        }
    }


    public Unit[] getUnitsList() {
        return Unit.values();
    }

    @Transactional
    public Unit getUnitByProductID(long ID) {
        Product p = productRepo.findProductByID(ID);
        return p.getUnit();
    }

    @Transactional
    public void setNewPrice(List<NewProductPriceDTO> list) {
        for (NewProductPriceDTO actualDTO : list) {
            Product p = productRepo.findProductByID(actualDTO.getID());
            p.setPrice(actualDTO.getNewPrice());
            productRepo.saveAndFlush(p);
        }
    }


}
