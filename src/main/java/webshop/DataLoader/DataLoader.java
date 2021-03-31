package webshop.DataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.Product.Category;
import webshop.Model.Product.Product;
import webshop.Model.Product.Unit;
import webshop.Model.UsersandRole.MyUser;
import webshop.Model.UsersandRole.Role;
import webshop.Repository.CategoryRepo;
import webshop.Repository.ProductRepo;
import webshop.Repository.RoleRepo;
import webshop.Repository.UserRepo;

import java.util.Locale;

@Component
public class DataLoader implements ApplicationRunner { //a run()-t lefuttatja a @SpringootApplication nel

    private Role userRole;
    private Role adminRole;

    private MyUser sanyi;

    private Product product1;
    private Product product2;
    private Product product3;

    private Category gyumolcs;
    private Category zoldseg;

    private RoleRepo roleRepo;
    private UserRepo userRepo;
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    @Autowired
    public DataLoader(RoleRepo roleRepo, UserRepo userRepo, ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.productRepo=productRepo;
        this.categoryRepo=categoryRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createRoles();
        createUser();
        createProducts();
        createCategories();
    }

    @Transactional
    public void createRoles() {
        if (roleRepo.count() == 0) {
            userRole = new Role();
            adminRole = new Role();
            userRole.setRoleName("user");
            adminRole.setRoleName("admin");
            adminRole.setID(1);
            adminRole = roleRepo.saveAndFlush(adminRole);
            userRole = roleRepo.saveAndFlush(userRole);
        } else {
            adminRole = roleRepo.findRoleByRoleName("admin");
            userRole = roleRepo.findRoleByRoleName("user");
        }
    }




    @Transactional
    public void createUser() {
        if (userRepo.count() == 0) {
            sanyi = new MyUser();
            sanyi.setActive(true);
            sanyi.setEmail("sanyi@gmail.hu");
            sanyi.setLocale(Locale.ENGLISH);
            sanyi.setFirstName("Sandor");
            sanyi.setLastName("nagy");
            sanyi = userRepo.saveAndFlush(sanyi);
        } else {
           sanyi = userRepo.findUserByFirstName("Sandor");
        }
    }

    @Transactional
    public void createProducts() {
        if (productRepo.count() == 0) {
            product1 = new Product();
            product1.setName("alma");
            product1.setDescription("nagyon finom piros alma");
            product1.setInPromotion(false);
            product1.setPrice(500);
            product1.setOutOfSeason(false);
            product1.setOutOfStock(false);
            product1.setLocale(Locale.CANADA);
            product1.setUnit(Unit.DARAB);
            product1.setCategoryID(1);
            product1= productRepo.saveAndFlush(product1);

            product2 = new Product();
            product2.setName("körte");
            product2.setDescription("sárga édes körte");
            product2.setInPromotion(false);
            product2.setPrice(200);
            product2.setOutOfSeason(false);
            product2.setOutOfStock(false);
            product2.setLocale(Locale.US);
            product2.setUnit(Unit.CSOMAG);
            product2= productRepo.saveAndFlush(product2);

            product3 = new Product();
            product3.setName("lócitrom");
            product3.setDescription("egészseges barna locitrom");
            product3.setInPromotion(false);
            product3.setPrice(100);
            product3.setOutOfSeason(false);
            product3.setOutOfStock(false);
            product3.setLocale(Locale.US);
            product3.setUnit(Unit.ADAG);
            product3.setCategoryID(2);
            product3= productRepo.saveAndFlush(product3);

        } else {
            product1 = productRepo.findProductByName("alma");
            product2 = productRepo.findProductByName("körte");
            product2 = productRepo.findProductByName("lócitrom");
        }
    }

    @Transactional
    public void createCategories() {
        if (categoryRepo.count() == 0) {
            gyumolcs=new Category();
            gyumolcs.setCategoryName("gyumolcs");
            gyumolcs = categoryRepo.saveAndFlush(gyumolcs);

            zoldseg=new Category();
            zoldseg.setCategoryName("zöldség");
            zoldseg = categoryRepo.saveAndFlush(zoldseg);
        } else {
            zoldseg = categoryRepo.findCategoryByCategoryName("zoldseg");
        }
    }


}
