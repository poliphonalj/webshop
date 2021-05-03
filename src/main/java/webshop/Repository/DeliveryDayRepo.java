package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webshop.Model.DeliveryDay;
import webshop.Model.DeliveryGaps;

import java.util.List;

@Repository
public interface DeliveryDayRepo extends JpaRepository<DeliveryDay, Long> {
DeliveryDay findByDayOfTheMonth(long day);

//az available==true erteku gapokat kell kivenni findall helyett

 List<DeliveryDay> findAll();
 //List<DeliveryDay>findDeliveryDayByDeliveryGapsCounternNotNull();
//DeliveryDay rem
//List<DeliveryDay>findByDeliveryGapsDeliveryGapsIsAvailableTrue();

}
