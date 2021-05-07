package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.Order.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
   // List<OrderItem> sfindOrderItemsByOrderIDAndQAndQuantity


     //select name,sum quantity drom orderitem where orderid is in (select orderid from order where orderday=xx)



}
