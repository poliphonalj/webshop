package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void successfulRegistration(String firstname) throws MessagingException {
          String htmlMsg = "Helló, kedves <strong> "+firstname+"</strong>!<br>Köszönjük, hogy regisztráltál a " +
                  "<strong>farmfalat.hu</strong> weboldalon! Reméljük sok örömödetleled itt...";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        //helper.setText(htmlMsg, true);
        helper.setTo("poliphonalj@freemail.hu");//ide jon a username
        helper.setSubject("Sikeres regisztráció");

        javaMailSender.send(mimeMessage);
    }



}
