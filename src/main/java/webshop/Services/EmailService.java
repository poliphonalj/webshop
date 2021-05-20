package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import webshop.Model.UsersandRole.MyUser;
import webshop.Repository.UserRepo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    final JavaMailSender javaMailSender;
    public UserRepo userRepo;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.javaMailSender = javaMailSender;
    }

    public void successfulRegistration(String firstname, String username) throws MessagingException {
        String htmlMsg = "Helló, kedves <strong> " + firstname + "</strong>!<br>Köszönjük, hogy regisztráltál a " +
                "<strong>farmfalat.hu</strong> weboldalon! Reméljük sok örömödetleled itt...";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        //helper.setText(htmlMsg, true);

        //helper.setTo("zoltanmarai51@gmail.com");//ide jon a username
        helper.setTo("poliphonalj@freemail.hu");//ide jon a username
        helper.setTo("zoltanmarai51@gmail.com");
        helper.setSubject("Sikeres regisztráció");

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

    public void forgotPassword(String link, long userID) throws MessagingException {
        System.out.println(userID);
        MyUser m = userRepo.findUserByUserID(userID);
        String firstName = m.getFirstName();
        String username = m.getUsername();
        System.out.println("visszatert");

        String htmlMsg = " <strong> " + firstName + "</strong>!<br> elfelejtette a jelszavat a " +
                "<strong>farmfalat.hu</strong> -n. Kérjük kattintson a kovetkezo linkre <br>" +
                "<a href=" + link + ">elfelejtett jelszo csere</a></br>";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        //helper.setText(htmlMsg, true);
        helper.setTo("poliphonalj@freemail.hu");//ide jon a username
        helper.setSubject("Jelszó módosítás");
        javaMailSender.send(mimeMessage);

    }


}
