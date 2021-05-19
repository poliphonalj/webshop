package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webshop.Model.Partner;

import java.util.HashMap;

@RestController
public class AboutUsController {

    @GetMapping("/aboutUs")
    public ResponseEntity<?> getAboutUs() {
        Partner zoli=new Partner();
        String title = "Rólunk";
        String text = "A Farmfalat.hu három fiatal ember által megálmodott és kivitelezett, kerttől házig történő zöldség-gyümölcs\n" +
                "kiszállítást végző vállalkozás.\n" +
                "Termék kínálatunk a következőkből épül fel:\n" +
                "    zöldség\n" +
                "    gyümölcs\n" +
                "    méz\n" +
                "    tea\n" +
                "    savanyúság\n" +
                "    cserepes virág\n" +
                "    exkluzív termékek - lekvárok szörpök mustárok jammek stb\n" +
                "\n" +
                "Csapatunk fiatalos és dinamikus emberekből áll, akiknek ugyanaz a küldetése:\n" +
                "minnél több ember konyhájába eljuttatni a háztáji magyar termékeket, és fogyasztásukkal örömet okozni. Filozófiánk\n" +
                "egyben egy gasztro-nevelés is:\n" +
                "Szeretnénk bebizonyítani mindenkinek, hogy a magyar élelmiszer finom és egészséges. Ösztönözni szeretnénk Titeket\n" +
                "arra, hogy minőségi élelmiszereket fogyasszatok, hiszen ahogy a mondás tartja:\n" +
                "\"az vagy, amit megeszel\"\n" +
                "\n" +
                "Olyan földrajzi adottságokkal rendelkező országban élünk, amely kivételes minőségű zöldséget és gyümölcsöt terem.\n" +
                "A magyar víz ásványi anyag tartalma és minősége világszinten páratlan. Ötvözve ezt a két adottságot születik meg a magyar gyümölcs\n" +
                "és gyümölcs.\n" +
                "Ezt szeretnék elhozni Nektek, egyenesen a konyhátokba.\n" +
                "Garantáltan jó minőségű termékekkel foglalkozunk, mert csak így vehetjük fel a versenyt a nagy üzletláncokkal.\n" +
                "\n" +
                "\n" +
                "Nem kell a zsúfolt piacon sorba állnod.\n" +
                "Nem kell a nehéz csomagokat cipelned.\n" +
                "Nem kell  a gyereket másra bíznod, hogy bevásárolj.\n" +
                "Nem kell a kocsiban várakozni a csúcsforgalomban.\n" +
                "Nem terheled minden gépkocsis bevásárlással a környezetünket.\n" +
                "\n" +
                "Adj egy esélyt a magyar gazdáknak is.....\n" +
                "\n" +
                "Jól szervezett weblapunkon pillanatokon belül megrendelheted a kívánt termékeket, kényelmesen bárhonnan, bármikor.\n" +
                "Mi házhoz is hozzuk Neked.";

        HashMap<String,String>hmap=new HashMap<>();
        hmap.put("title",title);
        hmap.put("subject",text);
        return ResponseEntity.ok().body(hmap);
    }
}
