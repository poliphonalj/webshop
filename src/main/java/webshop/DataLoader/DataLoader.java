package webshop.DataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.DeliveryDay;
import webshop.Model.DeliveryGaps;
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
    private DeliveryGapsRepo deliveryGapsRepo;
    private DeliveryDayRepo deliveryDayRepo;

    @Autowired
    public DataLoader(RoleRepo roleRepo, UserRepo userRepo, ProductRepo productRepo, CategoryRepo categoryRepo, SloganRepo sloganRepo, AddressRepo addressRepo, DeliveryGapsRepo deliveryGapsRepo, DeliveryDayRepo deliveryDayRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.sloganRepo = sloganRepo;
        this.addressRepo = addressRepo;
        this.deliveryGapsRepo = deliveryGapsRepo;
        this.deliveryDayRepo = deliveryDayRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createCategories();
        createRoles();
        createUser();
        createSlogan();
        createProducts();
        createDeliveryDate();
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

            Address a1 = new Address();
            a1.setAddressType(AddressType.HOME_ADDRESS);
            a1.setPostCode(1141);
            a1.setCity("budapest");
            a1.setSimpleAddress("siraly utca 21");
            a1.setMyUser(sanyi);

            Address a2 = new Address();
            a2.setAddressType(AddressType.DELIVERY_ADDRESS);
            a2.setPostCode(2110);
            a2.setCity("budapest");
            a2.setSimpleAddress("sarok ut 2");
            a2.setMyUser(sanyi);


            Address a3 = new Address();
            a3.setAddressType(AddressType.BILLING_ADDRESS);
            a3.setPostCode(1111);
            a3.setCity("budapest");
            a3.setSimpleAddress("bolonbika ut 2");
            a3.setMyUser(sanyi);

            Address a4 = new Address();
            a4.setAddressType(AddressType.HOME_ADDRESS);
            a4.setPostCode(1141);
            a4.setCity("budapest");
            a4.setSimpleAddress("home utca 21");
            a4.setMyUser(sanyi2);

            Address a5 = new Address();
            a5.setAddressType(AddressType.DELIVERY_ADDRESS);
            a5.setPostCode(2110);
            a5.setCity("budapest");
            a5.setSimpleAddress("deliveery ut 2");
            a5.setMyUser(sanyi2);


            Address a6 = new Address();
            a6.setAddressType(AddressType.BILLING_ADDRESS);
            a6.setPostCode(1111);
            a6.setCity("billingbudapest");
            a6.setSimpleAddress("billing ut 2");
            a6.setMyUser(sanyi2);


            sanyi = userRepo.saveAndFlush(sanyi);
            sanyi2 = userRepo.saveAndFlush(sanyi2);

            addressRepo.saveAndFlush(a1);
            addressRepo.saveAndFlush(a2);
            addressRepo.saveAndFlush(a3);

            addressRepo.saveAndFlush(a4);
            addressRepo.saveAndFlush(a5);
            addressRepo.saveAndFlush(a6);


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

    @Transactional
    public void createDeliveryDate() {
        DeliveryDay d = new DeliveryDay(2021, "május", 6, "csütörtök");
        d.setDayOfTheYear(126);
        DeliveryDay d2 = new DeliveryDay(2021, "május", 8, "szombat");
        d2.setDayOfTheYear(128);

        DeliveryGaps gap1 = new DeliveryGaps(8);
        gap1.setDeliveryDay(d);

        DeliveryGaps gap2 = new DeliveryGaps(10);
        gap2.setDeliveryDay(d);

        DeliveryGaps gap3 = new DeliveryGaps(12);
        gap3.setDeliveryDay(d);

        DeliveryGaps gap4 = new DeliveryGaps(14);
        gap4.setDeliveryDay(d);

        DeliveryGaps gap5 = new DeliveryGaps(16);
        gap5.setDeliveryDay(d);

        DeliveryGaps gap6 = new DeliveryGaps(18);
        gap6.setDeliveryDay(d);

        DeliveryGaps gap7 = new DeliveryGaps(20);
        gap7.setDeliveryDay(d);


        DeliveryGaps gap8 = new DeliveryGaps(8);
        gap8.setDeliveryDay(d2);

        DeliveryGaps gap9 = new DeliveryGaps(10);
        gap9.setDeliveryDay(d2);

        DeliveryGaps gap10 = new DeliveryGaps(12);
        gap10.setDeliveryDay(d2);

        DeliveryGaps gap11 = new DeliveryGaps(14);
        gap11.setDeliveryDay(d2);

        DeliveryGaps gap12 = new DeliveryGaps(16);
        gap12.setDeliveryDay(d2);

        DeliveryGaps gap13 = new DeliveryGaps(18);
        gap13.setDeliveryDay(d2);

        DeliveryGaps gap14 = new DeliveryGaps(20);
        gap14.setDeliveryDay(d2);


        List<DeliveryGaps> gapList = new ArrayList<>();
        List<DeliveryGaps> gapList2 = new ArrayList<>();
        gapList.add(gap1);
        gapList.add(gap2);
        gapList.add(gap3);
        gapList.add(gap4);
        gapList.add(gap5);
        gapList.add(gap6);
        gapList.add(gap7);

        gapList2.add(gap8);
        gapList2.add(gap9);
        gapList2.add(gap10);
        gapList2.add(gap11);
        gapList2.add(gap12);
        gapList2.add(gap13);
        gapList2.add(gap14);

        d.setListOfGaps(gapList);
        d2.setListOfGaps(gapList2);

        deliveryDayRepo.saveAndFlush(d);
        deliveryDayRepo.saveAndFlush(d2);


        deliveryGapsRepo.saveAndFlush(gap1);
        deliveryGapsRepo.saveAndFlush(gap2);
        deliveryGapsRepo.saveAndFlush(gap3);
        deliveryGapsRepo.saveAndFlush(gap4);
        deliveryGapsRepo.saveAndFlush(gap5);
        deliveryGapsRepo.saveAndFlush(gap6);
        deliveryGapsRepo.saveAndFlush(gap7);

        deliveryGapsRepo.saveAndFlush(gap8);
        deliveryGapsRepo.saveAndFlush(gap9);
        deliveryGapsRepo.saveAndFlush(gap10);
        deliveryGapsRepo.saveAndFlush(gap11);
        deliveryGapsRepo.saveAndFlush(gap12);
        deliveryGapsRepo.saveAndFlush(gap13);
        deliveryGapsRepo.saveAndFlush(gap14);

    }


}
