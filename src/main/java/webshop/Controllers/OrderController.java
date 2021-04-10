package webshop.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import webshop.Model.Order.Order;
import webshop.Services.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService service;
	
	@GetMapping("/orders/all")
	public List<Order> getAll(){
		return this.service.getAll();
	}
}
