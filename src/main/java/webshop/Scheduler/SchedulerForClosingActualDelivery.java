package webshop.Scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//import webshop.Services.DeliveryService;

@Component
public class SchedulerForClosingActualDelivery {
        //private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    //DeliveryService deliveryService;

    @Autowired
   // public SchedulerForClosingActualDelivery(DeliveryService deliveryService) {
   //     this.deliveryService = deliveryService;
  //  }

    @Scheduled(cron = "0 31 16 * * ?")//every day at 10
        public void run() {
        System.out.println("its 16:00");
            //log.info("The time is now {}", dateFormat.format(new Date()));
        //deliveryService.setUp();
        }
}
