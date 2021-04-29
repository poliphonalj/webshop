package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.DeliveryGaps;

import java.util.List;

@Repository
public interface DeliveryGapsRepo extends JpaRepository<DeliveryGaps, Long> {
    //DeliveryGaps findDeliveryGapsByDeliveryDayDeliveryDayID(long deliveryDayID);
DeliveryGaps findDeliveryGapsByDeliveryGapsIDAndDeliveryDayDeliveryDayID(long deliveryGapsID, long deliveryDayID);
   // List<DeliveryGaps> findAllByDeliveryDayDeliveryDayIDAndIsAvailableTrue(long deliveryDayID);
}
