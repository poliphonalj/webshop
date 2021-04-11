package webshop.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webshop.Model.Order.Order;
import webshop.Services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService service;

	@GetMapping("/all")
	public List<Order> getAll() {
		return this.service.getAll();
	}

	@PostMapping("/save")
	public Order save(@RequestBody Order order) {
		order = this.service.save(order);
		return order;
	}
}
