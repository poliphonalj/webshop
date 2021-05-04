package webshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

//kiszállitás napja

@Entity
public class DeliveryDay implements Comparable<DeliveryDay>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long deliveryDayID;
    private int year;
    private String month;
    private int dayOfTheMonth;
    private String dayOfWeek;
    @OneToMany(mappedBy = "deliveryDay")
    private List<DeliveryGaps>listOfGaps;

    private boolean isActive;
private int dayOfTheYear= LocalDate.now().getDayOfYear();


    public DeliveryDay() {
        isActive=true;
    }

    public DeliveryDay(int year, String month, int dayOfTheMonth, String dayOfWeek) {
        this.year = year;
        this.month = month;
        this.dayOfTheMonth = dayOfTheMonth;
        this.dayOfWeek = dayOfWeek;
        isActive=true;
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


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getDayOfTheYear() {
        return dayOfTheYear;
    }

    public void setDayOfTheYear(int dayOfTheYear) {
        this.dayOfTheYear = dayOfTheYear;
    }

    @Override
    public String toString() {
        return "DeliveryDay{" +
                "deliveryDayID=" + deliveryDayID +
                ", year=" + year +
                ", month='" + month + '\'' +
                ", dayOfTheMonth=" + dayOfTheMonth +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", listOfGaps=" + listOfGaps +
                '}';
    }

    @Override
    public int compareTo(DeliveryDay deliveryDay) {
        if(dayOfTheYear>=deliveryDay.dayOfTheYear)
            return 1;
        else
            return -1;
    }
}

