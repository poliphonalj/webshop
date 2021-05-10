package webshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;

@Entity

public class DeliveryGaps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryGapsID;
    private long gapStartsAt;//itt megtudom szamolni hogy hanyan kezdodnek mondjuk 8 kor
    private boolean isAvailable;
    private String deliveryGapString;
    private long counter=0;
    @ManyToOne
    @JsonIgnore
    DeliveryDay deliveryDay;


    public DeliveryGaps() {
    }

    public DeliveryGaps(long gapStartsAt) {
        this.gapStartsAt = gapStartsAt;
        isAvailable = true;
        deliveryGapString=gapStartsAt+" és "+(gapStartsAt+2)+" között";
    }


    public long getDeliveryGapsID() {
        return deliveryGapsID;
    }

    public void setDeliveryGapsID(long deliveryGapsID) {
        this.deliveryGapsID = deliveryGapsID;
    }

    public long getGapStartsAt() {
        return gapStartsAt;
    }

    public void setGapStartsAt(long gapStartsAt) {
        this.gapStartsAt = gapStartsAt;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public DeliveryDay getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(DeliveryDay deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public String getDeliveryGapString() {
        return deliveryGapString;
    }

    public void setDeliveryGapString(String deliveryGapString) {
        this.deliveryGapString = deliveryGapString;
    }


}