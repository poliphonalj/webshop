package webshop.DataLoader;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import webshop.Model.Partner;
import webshop.Model.Product.Category;
import webshop.Model.Product.Image;
import webshop.Model.Product.Product;
import webshop.Model.Product.Unit;
import webshop.Model.Slogan;
import webshop.Model.UsersandRole.Address;
import webshop.Model.UsersandRole.AddressType;
import webshop.Model.UsersandRole.MyUser;
import webshop.Model.UsersandRole.Role;
import webshop.Repository.*;
import webshop.Services.DeliveryService;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

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
    private Category dio, mez, szorp, tea, savanyusag, virag, exkluziv, csomagok;

    private RoleRepo roleRepo;
    private UserRepo userRepo;
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private SloganRepo sloganRepo;
    private AddressRepo addressRepo;
    private DeliveryGapsRepo deliveryGapsRepo;
    private DeliveryDayRepo deliveryDayRepo;
    private DeliveryService deliveryService;
    private PartnerRepo partnerRepo;
    private ImageRepo imageRepo;


    @Autowired
    public DataLoader(RoleRepo roleRepo, UserRepo userRepo, ProductRepo productRepo, CategoryRepo categoryRepo,
                      SloganRepo sloganRepo, AddressRepo addressRepo, DeliveryGapsRepo deliveryGapsRepo,
                      DeliveryDayRepo deliveryDayRepo, DeliveryService deliveryService, PartnerRepo partnerRepo,
                      ImageRepo imageRepo) {

        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.sloganRepo = sloganRepo;
        this.addressRepo = addressRepo;
        this.deliveryGapsRepo = deliveryGapsRepo;
        this.deliveryDayRepo = deliveryDayRepo;
        this.deliveryService = deliveryService;
        this.partnerRepo = partnerRepo;
        this.imageRepo = imageRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createCategories();
        createRoles();
        createUser();
        createSlogan();
        createProducts();
        createDeliveryDate();
        createPartners();


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

        s3.setText("r??pa retek mogyor??");
        s3.setActive(true);

        s4.setText("Az vagy, amit megeszel... L??gy ma egy ALMA!");
        s4.setActive(true);

        s5.setText("A vid??k ??ze a t??ny??rodban...");
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
    public void createPartners() {
        if (partnerRepo.count() == 0) {
            Partner p = new Partner();
            p.setName("R??ti Judit");
            p.setDescription("k??nyvel??s mesterfokon");
            p.setLink("www.jretijudit.hu");
            p.setPhone("112233");
            p.setActive(true);
            partnerRepo.saveAndFlush(p);

            Partner p2 = new Partner();
            p2.setName("M??rai Zolt??n");
            p2.setDescription("??rt??kes??t??si manager");
            p2.setLink("www.facebook/marai");
            p2.setPhone("112233");
            p2.setActive(true);
            p2.setUs(true);
            partnerRepo.saveAndFlush(p2);

            Partner p3 = new Partner();
            p3.setName("P??teri G??bor");
            p3.setDescription("beszerz??si munkat??rs");
            p3.setLink("www.facebook/peteri");
            p3.setPhone("112233");
            p3.setActive(true);
            p3.setUs(true);
            partnerRepo.saveAndFlush(p3);
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


    //product name|description|price|unit|categoryID|imageName
    @Transactional
    public void createProducts() throws IOException {
        try {
            File file = new File("src/main/resources/products.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String actualProductLine = sc.nextLine();
                String[] actualProductParts = actualProductLine.split(";");

                long id=uploadToDBTheProducts(actualProductParts);
                uplodTheImage(actualProductParts[5],id);

            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public long uploadToDBTheProducts(String[] array) {
        Product p = new Product();
        p.setName(array[0]);
        p.setDescription(array[1]);
        p.setPrice(Long.parseLong(array[2]));
        System.out.println(array[2] + "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        p.setUnit(Unit.valueOf(array[3]));


        p.setOutOfStock(false);
        p.setOutOfSeason(false);
        p.setInPromotion(false);
        p.setActive(true);

        Category cat = categoryRepo.findByCategoryName(array[4]);
        p.setCategory(cat);
        p.setCategoryID(cat.getID());

        productRepo.saveAndFlush(p);
        return productRepo.findProductByName(array[0]).getID();
    }

//meg kell egyezni a product es a picture namede ezt holnp meg vizsgalni jekk



    public void uplodTheImage(String imageName, long ID) throws IOException {


        File file = new File("src/main/resources/images/" + imageName + ".png");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(imageName,
                file.getName(), "image/png", IOUtils.toByteArray(input));

        Image dbImage = new Image();
        dbImage.setName(multipartFile.getName());
        dbImage.setByteFlow(multipartFile.getBytes());
        Product p=productRepo.findProductByID(ID);
        //Product p = productRepo.findProductByName(fileName);

        dbImage.setProduct(p);
        dbImage.setProductID(ID);//repo.findimagebyproductid miatt kell

        List<Image> list = new ArrayList<>();
        list.add(dbImage);
        p.setImageList(list);
        imageRepo.saveAndFlush(dbImage);
        productRepo.saveAndFlush(p);
    }





/*






        if(productRepo.count()==0)

    {
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
        product2.setName("k??rte");
        product2.setDescription("s??rga ??des k??rte");
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
        product3.setName("l??citrom");
        product3.setDescription("eg??szseges barna locitrom");
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

    } else

    {
        product1 = productRepo.findProductByName("alma");
        product2 = productRepo.findProductByName("k??rte");
        product2 = productRepo.findProductByName("l??citrom");
    }

}*/

    @Transactional
    public void createCategories() {
        if (categoryRepo.count() == 0) {
            gyumolcs = new Category();
            gyumolcs.setCategoryName("gy??m??lcs");
            gyumolcs = categoryRepo.saveAndFlush(gyumolcs);

            zoldseg = new Category();
            zoldseg.setCategoryName("z??lds??g");
            zoldseg = categoryRepo.saveAndFlush(zoldseg);

            dio = new Category();
            dio.setCategoryName("di??, mogyor?? ??s olajos magvak");
            dio = categoryRepo.saveAndFlush(dio);

            mez = new Category();
            mez.setCategoryName("tea, m??z, sz??rp, lekv??r");
            mez = categoryRepo.saveAndFlush(mez);


            savanyusag = new Category();
            savanyusag.setCategoryName("savany??s??g");
            savanyusag = categoryRepo.saveAndFlush(savanyusag);

            virag = new Category();
            virag.setCategoryName("vir??g");
            virag = categoryRepo.saveAndFlush(virag);

            exkluziv = new Category();
            exkluziv.setCategoryName("exkluz??v term??kek");
            exkluziv = categoryRepo.saveAndFlush(exkluziv);

            csomagok = new Category();
            csomagok.setCategoryName("csomagok");
            csomagok = categoryRepo.saveAndFlush(csomagok);


        } else {
            zoldseg = categoryRepo.findByCategoryName("zoldseg");
        }
    }


    @Transactional
    public void createDeliveryDate() {

        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        LocalDateTime l = LocalDateTime.now();

        //hetfon 10 ig leaddott rendelesre meg keddi kiszallitas lehet
        if (l.getDayOfWeek() == DayOfWeek.MONDAY && l.getHour() <= 9) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(1));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(3));
        }

        //hetfon 10 utan leadott rendeles kiszallitasa csut v szombat
        else if (l.getDayOfWeek() == DayOfWeek.MONDAY && l.getHour() > 9) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(3));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(5));
        }

        //kedd
        // kov kiszallitas csut v szompat
        else if (l.getDayOfWeek() == DayOfWeek.TUESDAY) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(2));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(4));
        }

        //szerdan 10 ig leaddott rendelesre meg csut kiszallitas lehet
        else if (l.getDayOfWeek() == DayOfWeek.WEDNESDAY && l.getHour() < 9) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(1));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(3));
        }

        //szerdan 10 utan leadott rendeles kiszallitasa szombat  v kedd
        else if (l.getDayOfWeek() == DayOfWeek.WEDNESDAY && l.getHour() > 9) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(3));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(6));
        }

        //csutortok
        else if (l.getDayOfWeek() == DayOfWeek.THURSDAY) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(2));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(5));
        }

//pentek 10 ig
        else if (l.getDayOfWeek() == DayOfWeek.FRIDAY && l.getHour() < 9) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(1));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(4));
        }

        //pentek10 utan leadott rendeles kiszallitasa kedd v csut
        else if (l.getDayOfWeek() == DayOfWeek.FRIDAY && l.getHour() > 9) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(4));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(6));


            //szombaton leadott rendeles kedd v csut
        } else if (l.getDayOfWeek() == DayOfWeek.SATURDAY) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(3));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(5));


        } else if (l.getDayOfWeek() == DayOfWeek.SUNDAY) {
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(2));
            deliveryService.convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(4));
        }
    }
}

/*
    public List<File> copyImagesFromDirectory() {
        List<File> list = new ArrayList<>();
        File dir = new File("src/main/resources/images");
        for (File f : dir.listFiles(IMAGE_FILTER)) {
            BufferedImage img = null;
            list.add(f);
            try {
                img = ImageIO.read(f);

                // you probably want something more involved here
                // to display in your UI
                System.out.println("image: " + f.getName());
                System.out.println(" width : " + img.getWidth());
                System.out.println(" height: " + img.getHeight());
                System.out.println(" size  : " + f.length());

            } catch (final IOException e) {
                // handle errors here

            }
        }
        return list;

    }

    // filter to identify images based on their extensions
    public FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            if (name.endsWith(".png")) {
                return (true);
            }
            return (false);
        }
    };


    public void createImages(List<File> list) throws IOException {


        File file2 = new File("src/main/resources/k??rte.png");
        FileInputStream input2 = new FileInputStream(file2);
        MultipartFile multipartFile2 = new MockMultipartFile("korte",
                file2.getName(), "image/png", IOUtils.toByteArray(input2));

        Image dbImage2 = new Image();
        dbImage2.setName(multipartFile2.getName());
        dbImage2.setByteFlow(multipartFile2.getBytes());
        dbImage2.setProduct(productRepo.findAll().get(1));
        dbImage2.setProductID(productRepo.findAll().get(1).getID());//repo.findimagebyproductid miatt kell

        Product p2 = productRepo.findAll().get(1);
        List<Image> list2 = new ArrayList<>();
        list2.add(dbImage2);
        p2.setImageList(list2);
        imageRepo.saveAndFlush(dbImage2);
        productRepo.saveAndFlush(p2);


    }
}*/


