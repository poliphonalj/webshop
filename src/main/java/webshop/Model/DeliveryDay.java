package webshop.Model;

import javax.persistence.*;
import java.util.List;

//kiszállitás napja

@Entity
public class DeliveryDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long deliveryDayID;
    private int year;
    private String month;
    private int dayOfTheMonth;
    private String dayOfWeek;
    @OneToMany(mappedBy = "deliveryDay")
    private List<DeliveryGaps>listOfGaps;




    public DeliveryDay() {
    }

    public DeliveryDay(int year, String month, int dayOfTheMonth, String dayOfWeek) {
        this.year = year;
        this.month = month;
        this.dayOfTheMonth = dayOfTheMonth;
        this.dayOfWeek = dayOfWeek;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDayOfTheMonth() {
        return dayOfTheMonth;
    }

    public void setDayOfTheMonth(int dayOfMonth) {
        this.dayOfTheMonth = dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


    public long getDeliveryDayID() {
        return deliveryDayID;
    }

    public void setDeliveryDayID(long deliveryDayID) {
        this.deliveryDayID = deliveryDayID;
    }

    public List<DeliveryGaps> getListOfGaps() {
        return listOfGaps;
    }

    public void setListOfGaps(List<DeliveryGaps> listOfGaps) {
        this.listOfGaps = listOfGaps;
    }
}