package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import webshop.Model.Orders.Orders;
import webshop.Model.UsersandRole.MyUser;
import webshop.Model.WantEmailNews;
import webshop.Repository.UserRepo;
import webshop.Repository.WantEmailRepo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {
    final JavaMailSender javaMailSender;
    public UserRepo userRepo;
    private WantEmailRepo wrepo;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserRepo userRepo, WantEmailRepo wrepo) {
        this.userRepo = userRepo;
        this.javaMailSender = javaMailSender;
        this.wrepo = wrepo;
    }

    public void successfulRegistration(String firstname, String username) throws MessagingException {
        String htmlMsg =
                "<body bgcolor=\"#ff704d\"  >" +
                        "<img src=\"src/main/resources/farmfalat2.png\" alt=\"FarmFalat.hu\"><br>" +
                        "Helló, kedves <strong> " + firstname + "</strong>!<br>Köszönjük, hogy regisztráltál a " +
                        "<strong>farmfalat.hu</strong> weboldalon! Reméljük sok örömödetleled itt..." +
                        "</body>";


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        //helper.setText(htmlMsg, true);

        //helper.setTo("zoltanmarai51@gmail.com");//ide jon a username
        // helper.setTo(username);
        helper.setTo("poliphonalj@freemail.hu");//ide jon a username
        helper.setTo("zoltanmarai51@gmail.com");
        helper.setSubject("Sikeres regisztráció");
        javaMailSender.send(mimeMessage);
    }

    public void sendOutAnOrder(Orders orders) throws MessagingException {
        int sum = 0;
        String htmlInLoop = "";
        String htmlMsg =

                "<body bgcolor=\"#ff704d\"  >" +

                        "<img src=\"src/main/resources/farmfalat2.png\" alt=\"FarmFalat.hu\">" +
                        "<br>" +
                        "Kedves  <strong> " + orders.getFirstName() + "</strong>!<br>A " +
                        "<strong>farmfalat.hu</strong> -ra leadott rendelése megérkezett hozzánk!<br><br>" +


                        "Az Ön által leadott rendelés adatai:" +
                        "<table border=1>" +
                        "<tr>" +
                        "<td>" + orders.getLastName() + "</td>" +
                        "<td>" + orders.getFirstName() + "</td>" +
                        "</tr>" +

                        "<tr>" +
                        "<td> szállítási cím: </td>" +
                        "<td>" + orders.getPostCode_delivery() + " " + orders.getCity_delivery() + " " + orders.getSimpleAddress_delivery() + "</td>" +
                        "</tr>" +

                        "<tr>" +
                        "<td> telefonszám : </td>" +
                        "<td>" + orders.getPhoneNumber() + "</td>" +
                        "</tr>" +

                        "<tr>" +
                        "<td> kiszállítás dátuma : </td>" +
                        "<td>" + orders.getDeliveryDayID() + " " + orders.getDeliveryGapsID() + "</td>" +
                        "</tr>" +


                        "<tr>" +
                        "<td> fizetés státusza : </td>" +
                        "<td>" + orders.isStatus() + "</td>" +
                        "</tr>";


        for (int i = 0; i < orders.getOrdersItemList().size(); i++) {

            htmlInLoop += (
                    "<tr>" +
                            "<td> " + orders.getOrdersItemList().get(i).getName() + ": " + " </td>" +
                            "<td>" + orders.getOrdersItemList().get(i).getQuantity() + " " + orders.getOrdersItemList().get(i).getUnit() + "</td>" +
                            "<td>" + (orders.getOrdersItemList().get(i).getPrice() * orders.getOrdersItemList().get(i).getQuantity()) + "</td>" +
                            "</tr>"
            );
            sum += (orders.getOrdersItemList().get(i).getPrice() * orders.getOrdersItemList().get(i).getQuantity());

        }


        String htmlEnd =
                "<tr>" +
                        "<td><strong> összesen: </strong></td>" +
                        "<td></td>" +
                        "<td><strong>" + sum + " Ft</strong></td>" +
                        "</tr>" +

                        "<tr>" +
                        "<td> + szállítás : </td>" +
                        "<td></td>" +
                        "<td>" + orders.getDeliveryFee() + " Ft</td>" +
                        "</tr>" +


                        "<tr>" +
                        "<td><strong> teljes fizetendő összeg: </td>" +
                        "<td></td>" +
                        "<td>" + (orders.getDeliveryFee() + sum) + " Ft</strong></td>" +
                        "</tr>" +
                        "</table>" +

                        "</body>";


        htmlMsg += htmlInLoop;

        htmlMsg += htmlEnd;
        System.out.println(htmlMsg);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        //helper.setText(htmlMsg, true);

        //helper.setTo("zoltanmarai51@gmail.com");//ide jon a username
        // helper.setTo(username);
        helper.setTo("poliphonalj@freemail.hu");//ide jon a username
        //helper.setTo("zoltanmarai51@gmail.com");
        helper.setSubject("Sikeres vásárlás");
        javaMailSender.send(mimeMessage);
    }


    public void newPassword(String password, long userID) throws MessagingException {
        MyUser myUser = userRepo.findUserByUserID(userID);
        String username = myUser.getUsername();
        String firstName = myUser.getFirstName();

        String htmlMsg = " <strong> " + firstName + "</strong>!<br> jelszót módosítottál a " +
                "<strong>farmfalat.hu</strong> -n. Az új jelszavad: <br>" + password + "</br>";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        //helper.setText(htmlMsg, true);
        helper.setTo("poliphonalj@freemail.hu");//ide jon a username
        helper.setSubject("Jelszó módosítás");

        javaMailSender.send(mimeMessage);
    }

    public void forgotPassword(String link, String email) throws MessagingException {
        MyUser m = userRepo.findUserByUsername(email);
        String firstName = m.getFirstName();
        String username = m.getUsername();


        String htmlMsg = " <body bgcolor=\"#ff704d\" ><img src=\"src/main/resources/farmfalat2.png\" alt=\"FarmFalat.hu\">" +
                "<br> Kedves  <strong> " + firstName + "</strong>!<br><br> Úgy tűnik, hogy elfelejtetted a jelszavadat a " +
                "<strong>farmfalat.hu</strong> -n. Kérjük kattints a kovetkezo linkre: <br>" +
                "<br><a href=" + link + ">elfelejtett jelszo csere</a></br> <br>És itt válassz egy új jelszót!" +
                "<br><br>Köszönettel: A FarmFalat.hu Csapata! </body>";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        //helper.setText(htmlMsg, true);
        //helper.setTo("poliphonalj@freemail.hu");//ide jon a username
        helper.setTo("zoltanmarai51@gmail.com");
        helper.setSubject("Elfelejtett jelszó");
        javaMailSender.send(mimeMessage);
    }


    public void sendNews(String text, List<String> list) throws MessagingException {
        String htmlMsg =
                "<body bgcolor=\"#ff704d\"  >" +
                        "<img src=\"https://farmfalatb.herokuapp.com/src/main/resources/farmfalat2.png\" alt=\"FarmFalat.hu\"><br>" +
                        "Szia! <br><br>"+text;


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        //helper.setText(htmlMsg, true);

        //helper.setTo("zoltanmarai51@gmail.com");//ide jon a username
        //

        for (String actualTo : list) {//actualto=emailaddress
            helper.setTo(actualTo);
           helper.setSubject("FarmFalat.hu hírlevél");
            WantEmailNews w= wrepo.findWantEmailNewsByEmail(actualTo);



//itt van a link majd ahonnan torolheti magat
htmlMsg+=(" kattint ide ha törölni szeretnéd magad a hírlevélről!  <FORM action=\"https://farmfalatb.herokuapp.com/emailNews/signOff\" method=\"POST\">\n" +
        "    <input type=\"text\" class=\"form-control\"  disabled hidden  id=\"email\" name=\"email\" value="+actualTo+"><br><br>\n" +
        "    <input type=\"submit\" value=\"törlöm magam a hírlevélről\">\n" +
        "</FORM></BODY>");


            System.out.println(htmlMsg);
            mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        javaMailSender.send(mimeMessage);
        }


        //String[] arr = new String[list.size()];
        //for (int i = 0; i < list.size(); i++) {
        //   arr[i] = list.get(i).getEmail();
        //helper.setTo(arr);najd kesobb betennixxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        // ezt kesobb betenni
    }
    //save db at every night

    //helper.setTo("poliphonalj@freemail.hu");//ide jon a username
    // helper.setTo("zoltanmarai51@gmail.com");
    //helper.setTo("peteri@t-online.hu");

}


