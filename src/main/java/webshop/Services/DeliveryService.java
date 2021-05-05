package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.Model.DeliveryDay;
import webshop.Model.DeliveryGaps;
import webshop.Repository.DeliveryDayRepo;
import webshop.Repository.DeliveryGapsRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DeliveryService {
    @PersistenceContext
    EntityManager em;

    DeliveryDayRepo deliveryDayRepo;
    DeliveryGapsRepo deliveryGapsRepo;

    @Autowired
    public DeliveryService(DeliveryDayRepo deliveryDayRepo, DeliveryGapsRepo deliveryGapsRepo) {
        this.deliveryDayRepo = deliveryDayRepo;
        this.deliveryGapsRepo = deliveryGapsRepo;
    }


    //ide kell egy olyan metodus ami idozitve van a rendeles zarasokhoz
    //kiirja egy filba az eddigi rendeleseket
    //kitorli a db adatokat
    //meghivja az availableDeliveryDaysAndGapsForTheNextTwodays()
    //minden naphoz ad egy delivery gap listat, nullazva annak szamlalojat
//es az egeszet menti a db be

    //mindennap 10 kor meghivodik
    @Transactional
    public void setUp() throws IOException {
























        //adatok kimentese
//saveLastDeliveries();


//ben rudja torolni

       setUpDeliveryDaysAndGaps();


//setUpDeliveryDaysAndGapsForTheNextSeconTime();

    }


    //returns the available days and gaps
    public List<DeliveryDay> getAvailableDeliveries() {
        //mutatja az aktivat es apasszivat is
        List<DeliveryDay> list = deliveryDayRepo.findAll();
        return list;
    }

    //books a day and gap and updates the gaps counter
    @Transactional
    public void book(long deliveryDayID, long deliveryGapsID, long orderID) {
        //orderidba betenne

        //a 8as gaptol mar a 2 napra kell betenni


        DeliveryGaps dGaps = deliveryGapsRepo.findDeliveryGapsByDeliveryGapsIDAndDeliveryDayDeliveryDayID(deliveryGapsID, deliveryDayID);

        long counter = dGaps.getCounter();
        counter++;

        if (counter == 6) {
            dGaps.setAvailable(false);
        } else {
            dGaps.setCounter(counter);
        }
        deliveryGapsRepo.saveAndFlush(dGaps);
    }


    //creates the next 2 delivery empty day with full amount of available gaps
    public void setUpDeliveryDaysAndGaps() {
        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        LocalDateTime l = LocalDateTime.now();

        //hetfon 10 ig leaddott rendelesre meg keddi kiszallitas lehet
        if (l.getDayOfWeek() == DayOfWeek.MONDAY && l.getHour() <= 9) {

        }

        //hetfon 10 utan leadott rendeles kiszallitasa csut v szombat
        else if (l.getDayOfWeek() == DayOfWeek.MONDAY && l.getHour() > 9) {
            deleteTheFirstDelivery();
            convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(5));
        }

        //kedd
        // kov kiszallitas csut v szompat
        else if (l.getDayOfWeek() == DayOfWeek.TUESDAY) {
           // localDateTimeList.add(l.plusDays(2));
            //localDateTimeList.add(l.plusDays(4));
           // convertLocalDatetimeToDeliveryDateAndSaveToDB(localDateTimeList);
        }

        //szerdan 10 ig leaddott rendelesre meg csut kiszallitas lehet
        else if (l.getDayOfWeek() == DayOfWeek.WEDNESDAY && l.getHour() < 9) {
           // localDateTimeList.add(l.plusDays(1));
            //localDateTimeList.add(l.plusDays(3));
            //convertLocalDatetimeToDeliveryDateAndSaveToDB(localDateTimeList);
        }

        //szerdan 10 utan leadott rendeles kiszallitasa szombat  v kedd
        else if (l.getDayOfWeek() == DayOfWeek.WEDNESDAY && l.getHour() > 9) {
            deleteTheFirstDelivery();
            convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(6));
        }

        //csutortok
        else if (l.getDayOfWeek() == DayOfWeek.THURSDAY) {
           // localDateTimeList.add(l.plusDays(2));
            //localDateTimeList.add(l.plusDays(5));
            //convertLocalDatetimeToDeliveryDateAndSaveToDB(localDateTimeList);
        }

//pentek 10 ig
        else if (l.getDayOfWeek() == DayOfWeek.FRIDAY && l.getHour() < 9) {
           // localDateTimeList.add(l.plusDays(1));
            //localDateTimeList.add(l.plusDays(4));
            //convertLocalDatetimeToDeliveryDateAndSaveToDB(localDateTimeList);
        }

        //pentek10 utan leadott rendeles kiszallitasa kedd v csut
        else if (l.getDayOfWeek() == DayOfWeek.FRIDAY && l.getHour() > 9) {
            deleteTheFirstDelivery();
            convertLocalDatetimeToDeliveryDateAndSaveToDB(l.plusDays(6));

            //szombaton leadott rendeles kedd v csut
        } else if (l.getDayOfWeek() == DayOfWeek.SATURDAY) {
            // localDateTimeList.add(l.plusDays(3));
            //localDateTimeList.add(l.plusDays(5));
            // convertLocalDatetimeToDeliveryDateAndSaveToDB(localDateTimeList);
        }


    }



@Transactional

    //2db deliverydate t tartalmazo listat ad vissza
    public void convertLocalDatetimeToDeliveryDateAndSaveToDB(LocalDateTime actualLocalDateTime) {


            int year = actualLocalDateTime.getYear();
            int month = actualLocalDateTime.getMonthValue();
            int dayOfTheWeek = actualLocalDateTime.getDayOfWeek().getValue();
            int dayOftheMonth = actualLocalDateTime.getDayOfMonth();
            String day = null;
            String honap = null;

            switch (dayOfTheWeek) {
                case 1:
                    day = "hétfő";
                    break;
                case 2:
                    day = "kedd";
                    break;
                case 3:
                    day = "szerda";
                    break;
                case 4:
                    day = "csütörtök";
                    break;
                case 5:
                    day = "péntek";
                    break;
                case 6:
                    day = "szombat";
                    break;
                case 7:
                    day = "vasárnap";
                    break;
            }

            switch (month) {
                case 1:
                    honap = "január";
                    break;
                case 2:
                    honap = "február";
                    break;
                case 3:
                    honap = "március";
                    break;
                case 4:
                    honap = "április";
                    break;
                case 5:
                    honap = "majus";
                    break;
                case 6:
                    honap = "június";
                    break;
                case 7:
                    honap = "július";
                    break;
                case 8:
                    honap = "augusztus";
                    break;
                case 9:
                    honap = "szeptember";
                    break;
                case 10:
                    honap = "október";
                    break;
                case 11:
                    honap = "november";
                    break;
                case 12:
                    honap = "december";
                    break;
            }
            DeliveryDay d = new DeliveryDay(year, honap, dayOftheMonth, day);
            d.setDayOfTheYear(actualLocalDateTime.getDayOfYear());

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


            List<DeliveryGaps> gapList = new ArrayList<>();
            gapList.add(gap1);
            gapList.add(gap2);
            gapList.add(gap3);
            gapList.add(gap4);
            gapList.add(gap5);
            gapList.add(gap6);
            gapList.add(gap7);


            d.setListOfGaps(gapList);

            deliveryDayRepo.saveAndFlush(d);
            deliveryGapsRepo.saveAndFlush(gap1);
            deliveryGapsRepo.saveAndFlush(gap2);
            deliveryGapsRepo.saveAndFlush(gap3);
            deliveryGapsRepo.saveAndFlush(gap4);
            deliveryGapsRepo.saveAndFlush(gap5);
            deliveryGapsRepo.saveAndFlush(gap6);
            deliveryGapsRepo.saveAndFlush(gap7);



        //ezt minden rendeleslezaraskor torolni kell!!!!!!!


    }
//minden rendeles lezarasnal a szerver lefuttatja a nextdelivery days metodust ami ujrairja a dbt es beleteszi a ket kov szallitasi napot


    public void saveLastDeliveries()
            throws IOException {
        List<DeliveryDay> deliveryList = deliveryDayRepo.findAll();
//itt szurni kell  hogy azokat a deliverynapokat adja vissza ahol a gap counter nem 0
        String fileName = "sima";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(deliveryList.toString());
        writer.close();
    }

    @Transactional
    public void deleteTheFirstDelivery() {
        List<DeliveryDay> list = deliveryDayRepo.findAllByIsActiveTrue();
        Collections.sort(list);

        DeliveryDay ddy = list.get(0);
        ddy.setActive(false);//litorli
        //miert csak az elsot torli ki??????????????????????????????????????????????????????????????????????????????????????
//mert a napot nem torlom ki

        deliveryGapsRepo.deleteDeliveryGapsByDeliveryDayDeliveryDayIDAndDeliveryDayIsActiveFalse(ddy.getDeliveryDayID());
        //deliveryDayRepo.saveAndFlush(ddy);
        //setUpDeliveryDaysAndGapsForTheNextSeconTime();
    }
}

