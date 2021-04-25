package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.Model.Order.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
