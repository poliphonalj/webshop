package webshop.Scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import webshop.Services.DeliveryService;

import java.io.IOException;

@Component
public class SchedulerForClosingActualDelivery {
        //private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    DeliveryService deliveryService;

    @Autowired
    public SchedulerForClosingActualDelivery(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @Scheduled(cron = "0 0 10 * * ?")//every day at 10
        public void runOnceByDay() throws IOException {
        System.out.println("its 10:00");
            //log.info("The time is now {}", dateFormat.format(new Date()));
        deliveryService.setUp();
        }
}
