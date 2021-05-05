package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.Order.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
}
