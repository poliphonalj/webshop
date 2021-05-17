package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import webshop.Model.Orders.Orders;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {

    List<Orders>findAllByStatusFalse();
    List<Orders> findAll();
    List<Orders>findOrdersByDeliveryDayID(long ID);


}
