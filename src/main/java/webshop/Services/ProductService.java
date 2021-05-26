package webshop.Services;

//TODO
//tesztek a servicekrem
//atnezni

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.DTOs.EndOfPromotionDTO;
import webshop.DTOs.NewProductPriceDTO;
import webshop.DTOs.ProductsForPromotionDTO;
import webshop.DTOs.PromotedProductDTO;
import webshop.Exceptions.ProductAddedException;
import webshop.Model.Product.Category;
import webshop.Model.Product.Product;
import webshop.Model.Product.Unit;
import webshop.Repository.CategoryRepo;
import webshop.Repository.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }


    @Transactional
    public long addProduct(String productName, String productDescription, long productPrice, Unit productUnit,
                           Locale productLocale, boolean productIsInPromotion,
                           boolean productIsOutOfStock, boolean productIsOutOfSeason,
                           long categoryID
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
            // p.setCategoryID(productCategoryID);
            p.setInPromotion(productIsInPromotion);
            p.setOutOfStock(productIsOutOfStock);
            p.setOutOfSeason(productIsOutOfSeason);
            Category cat = categoryRepo.findCategoryByID(categoryID);
            p.setCategory(cat);
            p.setCategoryID(cat.getID());
            productRepo.saveAndFlush(p);

            return p.getID();
        }
    }


    @Transactional
    public void setPromotion(List<ProductsForPromotionDTO> list) {
        for (int i = 0; i < list.size(); i++) {
            Product p = productRepo.findProductByID(list.get(i).getID());
            p.setPromotedPrice(list.get(i).getPromotedPrice());
            p.setPromotionDescription(list.get(i).getPromotionDescription());
            p.setInPromotion(true);
            productRepo.saveAndFlush(p);
        }
    }

    @Transactional
    public List<PromotedProductDTO> getPromotedList() {
        List<PromotedProductDTO> listToReturn = new ArrayList<>();
        List<Product> list = productRepo.findByIsInPromotion(true);
        for (Product actualProduct : list) {
            listToReturn.add(new PromotedProductDTO(actualProduct.getID(), actualProduct.getPromotedPrice(),
                    actualProduct.getPrice(), actualProduct.getPromotionDescription()));
        }
        return listToReturn;
    }


    @Transactional
    public void setEndOfPromotion(List<EndOfPromotionDTO> list) {
        for (int i = 0; i < list.size(); i++) {
            Product p = productRepo.findProductByID(list.get(i).getID());
            p.setPrice(list.get(i).getNewPrice());
            p.setInPromotion(false);
            productRepo.saveAndFlush(p);
        }
    }

    @Transactional
    public void modifyProduct(Product product, long ID) {
        Product p = productRepo.findProductByID(ID);
        if (product.getName() != null) {
            p.setName(product.getName());
        }
        if (product.getDescription() != null) {
            p.setDescription(product.getDescription());
        }
        if (product.getPrice() != 0) {
            p.setPrice(product.getPrice());
        }
        if (product.getPrice() != 0) {
            p.setPrice(product.getPrice());
        }

        if (product.isInPromotion() != false) {
            p.setInPromotion(product.isInPromotion());
        }

        if (product.getPromotedPrice()!=0) {
            p.setPromotedPrice(product.getPromotedPrice());
        }

        if (product.isOutOfSeason()!=false) {
            p.setOutOfSeason(product.isOutOfSeason());
        }

        if(product.isOutOfStock()!=false){
            p.setOutOfStock(product.isOutOfStock());
        }

        if(product.getImageList()!=null){
            p.setImageList(product.getImageList());
        }

        if(product.getUnit()!=null){
            p.setUnit(product.getUnit());
        }

        if(product.getLocale()!=null){
            p.setLocale(product.getLocale());
        }


        productRepo.saveAndFlush(p);
    }


    public Unit[] getUnitsList() {
        return Unit.values();
    }

    @Transactional
    public Unit getUnitByProductID(long ID) {
        Product p = productRepo.findProductByID(ID);
        Unit u = p.getUnit();
        return u;
    }

    @Transactional
    public void setNewPrice(List<NewProductPriceDTO> list) {
        for (NewProductPriceDTO actualDTO : list) {
            Product p = productRepo.findProductByID(actualDTO.getID());
            p.setPrice(actualDTO.getNewPrice());
            productRepo.saveAndFlush(p);
        }
    }

    @Transactional
    public long getPrice(long ID) {
        Product p = productRepo.findProductByID(ID);
        return p.getPrice();
    }

    @Transactional
    public Product getProductByID(long ID) {
        Product p = productRepo.findProductByID(ID);
        return p;
    }

    @Transactional
    public List<Product> getProductsByName(String name) {
        List<Product> list = productRepo.findProductsByNameIsContaining(name);
        return list;
    }

    @Transactional
    public List<Product> getProductsByCategoryID(long ID) {
        return productRepo.findAllByCategoryIDAndIsOutOfSeasonFalseAndIsOutOfStockFalseAndIsActiveTrue(ID);
        // return productRepo.findAllByCategoryIDAndIsInPromotionAndIsOutOfSeasonFalseAndIsOutOfStockFalse(ID);///miert csak az almat adja ki

    }

    @Transactional
    public List<Product> getAllProducts() {
        List<Product> list = productRepo.findAll();
        return list;
    }

    @Transactional
    public void removeProduct(long ID) {
        Product p = productRepo.findProductByID(ID);
        p.setActive(false);
        productRepo.saveAndFlush(p);
    }

    @Transactional
    public void reactivateProduct(long ID) {
        Product p = productRepo.findProductByID(ID);
        p.setActive(true);
        productRepo.saveAndFlush(p);
    }
}
