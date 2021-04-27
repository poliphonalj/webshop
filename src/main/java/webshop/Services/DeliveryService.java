package webshop.Services;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import webshop.Model.DeliveryDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DeliveryService {
    public List<DeliveryDate> nextTwoDeliveryDays() {

        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        LocalDateTime l = LocalDateTime.now();

        //hetfon 10 ig leaddott rendelesre meg keddi kiszallitas lehet
        if (l.getDayOfWeek() == DayOfWeek.MONDAY && l.getHour() <= 9) {
            localDateTimeList.add(l.plusDays(1));
            localDateTimeList.add(l.plusDays(3));
           return convertLocalDatetimeToDeliveryDate(localDateTimeList);
        }

        //hetfon 10 utan leadott rendeles kiszallitasa csut v szombat
        else if (l.getDayOfWeek() == DayOfWeek.MONDAY && l.getHour() > 9) {
            localDateTimeList.add(l.plusDays(2));
            localDateTimeList.add(l.plusDays(4));
         return   convertLocalDatetimeToDeliveryDate(localDateTimeList);
        }

        //kedd
        // kov kiszallitas csut v szompat
        else if (l.getDayOfWeek() == DayOfWeek.TUESDAY) {
            localDateTimeList.add(l.plusDays(2));
            localDateTimeList.add(l.plusDays(4));
            return  convertLocalDatetimeToDeliveryDate(localDateTimeList);
        }

        //szerdan 10 ig leaddott rendelesre meg csut kiszallitas lehet
        else if (l.getDayOfWeek() == DayOfWeek.WEDNESDAY && l.getHour() <= 9) {
            localDateTimeList.add(l.plusDays(1));
            localDateTimeList.add(l.plusDays(3));
         return   convertLocalDatetimeToDeliveryDate(localDateTimeList);
        }

        //szerdan 10 utan leadott rendeles kiszallitasa csut v szombat
        else if (l.getDayOfWeek() == DayOfWeek.WEDNESDAY && l.getHour() > 9) {
            localDateTimeList.add(l.plusDays(2));
            localDateTimeList.add(l.plusDays(4));
         return   convertLocalDatetimeToDeliveryDate(localDateTimeList);
        }

        //csutortok
        else if (l.getDayOfWeek() == DayOfWeek.THURSDAY) {
            localDateTimeList.add(l.plusDays(2));
            localDateTimeList.add(l.plusDays(5));
         return   convertLocalDatetimeToDeliveryDate(localDateTimeList);
        }

//pentek 10 ig
        else if (l.getDayOfWeek() == DayOfWeek.FRIDAY && l.getHour() <= 9) {
            localDateTimeList.add(l.plusDays(1));
            localDateTimeList.add(l.plusDays(4));
         return    convertLocalDatetimeToDeliveryDate(localDateTimeList);
        }

        //pentek10 utan leadott rendeles kiszallitasa kedd v csut
        else if (l.getDayOfWeek() == DayOfWeek.WEDNESDAY && l.getHour() > 9) {
            localDateTimeList.add(l.plusDays(4));
            localDateTimeList.add(l.plusDays(6));
        return     convertLocalDatetimeToDeliveryDate(localDateTimeList);
        } else if (l.getDayOfWeek() == DayOfWeek.SATURDAY) {
            localDateTimeList.add(l.plusDays(3));
            localDateTimeList.add(l.plusDays(5));
         return    convertLocalDatetimeToDeliveryDate(localDateTimeList);
        } else if (l.getDayOfWeek() == DayOfWeek.SUNDAY) {
            localDateTimeList.add(l.plusDays(2));
            localDateTimeList.add(l.plusDays(4));
         return    convertLocalDatetimeToDeliveryDate(localDateTimeList);
        }
       return null;
    }

    public List<DeliveryDate> convertLocalDatetimeToDeliveryDate(List<LocalDateTime> list) {
       List<DeliveryDate>returnList=new ArrayList<>();
        for (LocalDateTime actualLocalDateTime : list) {
            int year = actualLocalDateTime.getYear();
            int month = actualLocalDateTime.getMonthValue();
            int dayOfTheWeek = actualLocalDateTime.getDayOfWeek().getValue();
            String day=null;
            String honap=null;

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
                    honap = "május";
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
         DeliveryDate d=new DeliveryDate(year,honap,dayOfTheWeek,day)   ;
           returnList.add(d);
        }
    return returnList;
    }


}

