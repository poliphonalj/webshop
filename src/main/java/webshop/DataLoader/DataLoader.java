package webshop.DataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.Product.Category;
import webshop.Model.Product.Product;
import webshop.Model.Product.Unit;
import webshop.Model.Slogan;
import webshop.Model.UsersandRole.Address;
import webshop.Model.UsersandRole.AddressType;
import webshop.Model.UsersandRole.MyUser;
import webshop.Model.UsersandRole.Role;
import webshop.Repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class DataLoader implements ApplicationRunner { //a run()-t lefuttatja a @SpringootApplication nel

    private Role userRole;
    private Role adminRole;

    private MyUser sanyi;
    private MyUser sanyi2;

    private Product product1;
    private Product product2;
    private Product product3;

    private Category gyumolcs;
    private Category zoldseg;

    private RoleRepo roleRepo;
    private UserRepo userRepo;
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private SloganRepo sloganRepo;
    private AddressRepo addressRepo;

    @Autowired
    public DataLoader(RoleRepo roleRepo, UserRepo userRepo, ProductRepo productRepo, CategoryRepo categoryRepo, SloganRepo sloganRepo, AddressRepo addressRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.sloganRepo = sloganRepo;
        this.addressRepo=addressRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createCategories();
        createRoles();
        createUser();
        createSlogan();
        createProducts();

    }

    @Transactional
    public void createSlogan() {
        Slogan s = new Slogan();
        Slogan s2 = new Slogan();
        Slogan s3 = new Slogan();
        Slogan s4 = new Slogan();
        Slogan s5 = new Slogan();

        s.setText("hello szlogen");
        s.setActive(true);

        s2.setText("hogyvagy szlogen");
        s2.setActive(true);

        s3.setText("répa retek mogyoró");
        s3.setActive(true);

        s4.setText("Az vagy, amit megeszel... Légy ma egy ALMA!");
        s4.setActive(true);

        s5.setText("A vidék íze a tányérodban...");
        s5.setActive(true);

        sloganRepo.saveAndFlush(s);
        sloganRepo.saveAndFlush(s2);
        sloganRepo.saveAndFlush(s3);
        sloganRepo.saveAndFlush(s4);
        sloganRepo.saveAndFlush(s5);
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
            sanyi.setUsername("admin@admin.hu");
            sanyi.setLocale(Locale.US);
            sanyi.setFirstName("admin");
            sanyi.setLastName("admin");
            sanyi.setPassword("admin");
            sanyi.setPhoneNumber("1233456");

            List<Role> list = new ArrayList<>();
            list.add(adminRole);
            sanyi.setRoleList(list);

            sanyi2 = new MyUser();
            sanyi2.setActive(true);
            sanyi2.setUsername("user@user.hu");
            sanyi2.setLocale(Locale.US);
            sanyi2.setFirstName("user");
            sanyi2.setLastName("user");
            sanyi2.setPassword("user");
            sanyi2.setPhoneNumber("1233456");


            List<Role> list2 = new ArrayList<>();
            list2.add(userRole);
            sanyi2.setRoleList(list2);

            Address a1=new Address();
            a1.setAddressType(AddressType.HOME_ADDRESS);
            a1.setPostCode("1141");
            a1.setCity("budapest");
            a1.setSimpleAddress("siraly utca 21");
            a1.setMyUser(sanyi);

            Address a2=new Address();
            a2.setAddressType(AddressType.DELIVERY_ADDRESS);
            a2.setPostCode("2110");
            a2.setCity("budapest");
            a2.setSimpleAddress("sarok ut 2");
            a2.setMyUser(sanyi);


            Address a3=new Address();
            a3.setAddressType(AddressType.HOME_ADDRESS);
            a3.setPostCode("1111");
            a3.setCity("budapest");
            a3.setSimpleAddress("bolonbika ut 2");
            a3.setMyUser(sanyi2);

            sanyi = userRepo.saveAndFlush(sanyi);
            sanyi2 = userRepo.saveAndFlush(sanyi2);

            addressRepo.saveAndFlush(a1);
            addressRepo.saveAndFlush(a2);
            addressRepo.saveAndFlush(a3);
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
            Category cat = categoryRepo.findCategoryByID(1);
            product1.setCategory(cat);
            product1.setCategoryID(cat.getID());

            product1 = productRepo.saveAndFlush(product1);

            product2 = new Product();
            product2.setName("körte");
            product2.setDescription("sárga édes körte");
            product2.setInPromotion(false);
            product2.setPrice(200);
            product2.setOutOfSeason(false);
            product2.setOutOfStock(false);
            product2.setLocale(Locale.US);
            product2.setUnit(Unit.CSOMAG);
            Category cat2 = categoryRepo.findCategoryByID(1);
            product2.setCategory(cat2);
            product2.setCategoryID(cat2.getID());

            product2 = productRepo.saveAndFlush(product2);


            product3 = new Product();
            product3.setName("lócitrom");
            product3.setDescription("egészseges barna locitrom");
            product3.setInPromotion(false);
            product3.setPrice(100);
            product3.setOutOfSeason(false);
            product3.setOutOfStock(false);
            product3.setLocale(Locale.US);
            product3.setUnit(Unit.ADAG);
            Category cat3 = categoryRepo.findCategoryByID(2);
            product3.setCategory(cat3);
            product3.setCategoryID(cat3.getID());

            product3 = productRepo.saveAndFlush(product3);

        } else {
            product1 = productRepo.findProductByName("alma");
            product2 = productRepo.findProductByName("körte");
            product2 = productRepo.findProductByName("lócitrom");
        }
    }

    @Transactional
    public void createCategories() {
        if (categoryRepo.count() == 0) {
            gyumolcs = new Category();
            gyumolcs.setCategoryName("gyumolcs");
            gyumolcs = categoryRepo.saveAndFlush(gyumolcs);

            zoldseg = new Category();
            zoldseg.setCategoryName("zöldség");
            zoldseg = categoryRepo.saveAndFlush(zoldseg);
        } else {
            zoldseg = categoryRepo.findByCategoryName("zoldseg");
        }
    }


}
