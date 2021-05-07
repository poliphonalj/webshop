package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webshop.Model.Order.Order;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order>findAllByStatusFalse();
    List<Order> findAll();
    List<Order>findOrdersByDeliveryDayID(long ID);

}
