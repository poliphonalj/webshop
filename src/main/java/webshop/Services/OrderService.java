package webshop.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webshop.Model.Order.Order;
import webshop.Repository.OrderRepo;

@Service
public class OrderService {
	@Autowired
	private OrderRepo orderRepo;
	
	public List<Order> getAll(){
		return this.orderRepo.findAll();
	}
	
	@Transactional
	public Order save(Order order) {
		return orderRepo.save(order);
	}
}
