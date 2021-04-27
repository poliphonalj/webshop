package webshop.Services;

import org.springframework.stereotype.Service;
import webshop.Model.Barion.Payment;

@Service
public class BarionService {
    public void barionPayment(Payment p){
        System.out.println("payment request");
    }

    public void getPaymentStatus(Payment p){

    }
}
