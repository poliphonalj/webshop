package webshop.Services;

import org.springframework.stereotype.Service;
import webshop.Model.Faq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





@Service
public class FaqService {
    private Faq f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20,f21, f22, f23, f24, f25, f26, f27;

    public FaqService() {
        f = new Faq("Most találkoztam a Farmfalat.hu weboldallal. Mi is ez?",
                " Online zöldség és gyümölcs rendelésekkel foglalkozó webshop, összepárosítva a kiszállítással.\n" +
                        "    Termékpalettánkon találsz szezonális magyar zöldséget, gyümölcsöt, mézet, teát, cserepes virágot,\n" +
                        "    szörpöket, savanyúságot és egyeb exkluzív termékeket pl xxxxxxx");

        f2 = new Faq("Miért jó nekem, ha a FarmFalat-hu webshopon rendelek?",
                " Nem kell a piacon sorban állnod, nem kell cipekedned, nem kell feleslegesen a dugóban ülnöd.\n" +
                        "    Ezzel szemben kényelmesen bárhonnan és bármikor megrendelheted a kívánt terméket.\n" +
                        "    A minőséggel sem kell bajlódnod, hiszen kipróbált partnerekkel állunk kapcsolatban, akik már sokszor bizonyítottak.\n" +
                        "    De ha ez sem győzőtt meg, akkor szimplán adj egy esélyt a magyar termelőknek. Termékeink legtöbbje magyar kis\n" +
                        "    vállalkozók munkájának gyümölcse:) Kiszállító járműveink naponta kb 200 címet szolgának ki. Ezzel jelentősen\n" +
                        "    tehermentesítik környezetünket, hiszen kétszáz személyautó környezet-terhelését mentesítjük egyetlen járművel.");

        f3 = new Faq("Be kell regisztrálnom a vásárláshoz?", " Nem, de mégis javasoljuk, hiszen így egyszerűbben tudsz vásárolni, és\n" +
                "    részt vehetsz a különböző akcióinkban is, melyek csak a regisztrált\n" +
                "    vásárlóinknak járnak. Regisztrálás nélkül csak online fizetést tudunk elfogadni.");


        f4 = new Faq("Milyen fizetési módok közül választhatok?", " Regisztrált vásárlóink választhatnak az átvételkori kártyás, készpénzes, vagy az online bankkártyás\n" +
                "    vásárlás között. Ha nem rgisztrálsz, akkor csak online bankkártyás fizetést tudunk elfogadni.");


        f5 = new Faq("Mikor kapom meg, amit rendeltem?", "Cégünk minden kedden, csütörtökön és szombaton tud kiszállítani. Saját magad választhatod ki a számodra legkedvezőbb\n" +
                "    dátumot, és napszakot.");


        f6 = new Faq("Nagyobb tételben szeretnék rendelni, ez is megoldható?", "Természetesen igen, ilyen esetekben érdemes minket telefonon megkeresni, mivel árainkat is tudjuk a\n" +
                "    megrendelés tételétől függően módosítani.");


        f7 = new Faq("Hol vehetem át a vásárolt termékeket?", "Kollégánk a kertből egészen a lakhelyedig kiszállítja a vásárolt termékeket,\n" +
                "    így nincs más dolgod, mint a kpuban átvenni tőle. A rendszer sajátossága, hogy\n" +
                "    szükség esetén meg tudja különböztetni a regisztrált lakcímedet az aktuális  kiszállítási\n" +
                "    címtől, így egyből rendelhetsz tőlünk a munkahelyedre, vagy akár a szüleidnek is.");


        f8 = new Faq("Mennyibe kerül a kiszállítás?", "Ez függ az adott szállítási címtől és a napszaktól is. Alapjaiban elmondható, hogy minden rendelés\n" +
                "    esetén a program kalkulál egy szállítási díjat, és ezt jól feltüntetve jelzi is Neked. Ha 5000Ft feletti\n" +
                "    rendelést dsz le, akkor Budapest területén ingyen házhoz visszük a termékeeidet.");


        f9 = new Faq("Kaphatok -e számlát?XX", "Igen, minden esetben kaphatsz ÁFÁs számlát, ha szükséged van rá??????????????????????");


        f10 = new Faq("Van minimálisan rendelendő mennyiség?", "Nálunk nincs, akár egy darab almát is elviszünk Neked. De a kiszállítás árában sajnos ezt kompenzálnink kell.");


        f11 = new Faq("Milyen csomagolásban érkeznek az általam vásárolt termékek?", "Minden termék kiszállításához a megfelelő csomagolást választjuk. Lehet műanyag tálca, faláda, zacskó stb....");


        f12 = new Faq("A shopban feltüntetett árak bruttóként értendőek?", "Abszolút mértékben igen!");


        f13=new Faq("Elfelejtettem a jelszavamat, és nem tudok rendelni? Mi a teendő?","Semmi gond, ez néha a legjobbakal is megesik. Kattints az elfelejtett jelszó fülre a ???? ban\n" +
                "    és a regisztrált email címedre fogunk küldeni egy emailt. Ez tartalmaz egy linket amire kattintva\n" +
                "    biztonságosan meg tudod változtatni a jelszavadat. Amennyiben nem érkezik email, érdemes a levelezőprogram\n" +
                "    spam mappájában is megnézni.");

        f14=new Faq("Ugye NEM tároljátok el a bankkártya adataimat?","Erről szó sincs, mi a Barion online fizető szolgáltatást használjuk, mely egyike a legbiztonságosabb rendszereknek.");

        f15=new Faq("Megváltoztak az adataim (név, lakcím, telefonszám) Most mit tegyek?","Aggodalomra semmi ok, hiszen az összes adatváltozásodat frissitheted a ????Profilom\n" +
                "    menüpont alatt. Ez alól kivétel az email cím. Azt nem engedi a rendszer változtatni.");


        f16=new Faq("Mi történik ha nem érkezik meg időben a rendelésem?"," Csúszások sajnos mindenhol előfordulhatnak. Mivel vállalkozásunk kiszállítás része erősen\n" +
                "    függ az aktuális forgalmi viszonyoktól, így csak az eddigi tapasztalatainkra bízhatjuk magunkat.\n" +
                "    Késés esetén a diszpécserünk felveszi Veled a kapcsolatot, és egy alternatív megoldásban segít Neked.");


        f17=new Faq("Honnan tudhatom, hogy finomak a termékeitek?","Igazábol erre mindig azt válaszoljuk, hogy próba cseresznye:) De általánosságban elmondható, hogy\n" +
                "    a hazai kistermelői zöldségek, és gyümölcsök sokkal ízletesebbek, mint a nagy áruházláncokban árult\n" +
                "    külföldi társaik. Úgy gondoljuk, hogy a bevásárlóközpontokkal nem árban hanem MINőSÉGBEN tudjuk felvenni\n" +
                "    a versenyt. Szóval próbáldd ki azt a cseresznyét.....:)");


        f18=new Faq("Nagyon tetszik ez a webshop, lehet nekem is ilyenem?"," Igen persze. Ezt az online piacot a XXXX Kft. készítette és üzemelteti. Itt egy link a srácokhoz.\n" +
                "    Köszönjük  a munkájukat nagyon elégedettek vagyunk velük. De ha mégis valami hibát vélsz felfedezni,\n" +
                "    kérlek ezen az email címen jelezd felejük.");


        f19=new Faq("Élvezhető és használható-e a webshop mobil telefonról?","Természetesen igen, hiszen mi is tudjuk hogy manapság az ilyen vásárlások jelentős része\n" +
                "    okostelefonról történik. Tehát a weblap kialakításánál ezt figyelembe vettük.");

        f20=new Faq("Vidéken élek, rendelhetek én is?","Egyenlőre kapacitásunk csak Budapest határain belülre korlátozódik. Úgy gondoljuk, hogy nem\n" +
                "    ronthatjuk termékeink minőségét azzal, hogy kapacitás szűkében távoli kiszállításokat erőltessünk.\n" +
                "    Budapest közeli települések megoldhatóak, de csak egyéni megbeszélés alapján.");

        f22=new Faq("Azokat a termékeket kapom amik a fotókon vannak?","A fotók csak tájékoztató jellegűek, tehát a képeken látható csomagolás vagy kiszerelés nem mindig az aktuálisnak\n" +
                "    megfelelő.");

        f23=new Faq("Kapok email megerősítést a vásárlásomról?","Igen, rendszerünk minden sikeres vásárlás után küld egy emlékeztető emailt.");

        f24=new Faq("Megbetegedtem és nem tudom átvenni a rendelésemet, mi a teendőm?"," A rendeléseknek van egy határidejűk, ami után már nem tudjuk azokat módosítani, hiszen az egyéb logisztikai\n" +
                "    folyamatok, például beszerzés vagy csomagolás már mgindultak. Vész esetén telefonon, személyes megkeresés esetén\n" +
                "    bele tudunk nyúlni a rendszerbe és meg tudjuk változtatni a szállítás címét vagy idejét. De szeretnénk kérni Titeket,\n" +
                "    hogy tiszteljetek meg minket a korrekt átvétel megszervezésével. köszönjük:)");

        f25=new Faq("Céges rendezvényeket, szülinapokat,esküvőket, tárgyalásokat, gyerekzsúrokat is ki tudtok szolgálni?","Teljes mértékben. Idén már több hasonló megkeresésünk is volt, és a jól sikerült bulik után a szervezők méltán\n" +
                "    aratták le munkájuk gyümölcsét. (amiket mi szaáálítottunk nekik).\n" +
                "    Ilyen jellegű megkeresést telefonon, vagy emailben történő megkeresés és egyeztetés után áll\n" +
                "    módunkban elfogadni.");

        f26=new Faq("Egészen az ajtómig hozzátok a rendelésemet","Igen, de csak ésszerű határokon belül. Olyan helyen ahova nem tudunk szállító járműveinkkel behajtani,\n" +
                "    nem áll módunka kiszállítani sem.");

        f27=new Faq("Csak Forintban fizethetek?","Szívesen elfogadnánk arany rudat, arany fogat, laptopot fizetésként, de sajnos ezt a hatályos magyar jogszabályok\n" +
                "    nem teszik lehetővé, szóval maradunk a Forintnál.");




    }


    public List<Faq> getFaqs() {
        List<Faq> faqList = new ArrayList<>();
        faqList.add(f);
        faqList.add(f2);
        faqList.add(f3);
        faqList.add(f4);
        faqList.add(f5);
        faqList.add(f6);
        faqList.add(f7);
        faqList.add(f8);
        faqList.add(f9);
        faqList.add(f10);
        faqList.add(f11);
        faqList.add(f12);
        faqList.add(f13);
        faqList.add(f14);
        faqList.add(f15);
        faqList.add(f16);
        faqList.add(f17);
        faqList.add(f18);
        faqList.add(f19);
        faqList.add(f20);
        faqList.add(f11);
        faqList.add(f22);
        faqList.add(f23);
        faqList.add(f24);
        faqList.add(f25);
        faqList.add(f26);
        faqList.add(f27);


        return faqList;
    }


}
