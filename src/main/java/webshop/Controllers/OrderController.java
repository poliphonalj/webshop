package webshop.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webshop.Model.Order.Order;
import webshop.Services.OrderService;
import webshop.exception.WebshopException;

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
	public Order save(@Valid @RequestBody Order order, BindingResult result) throws WebshopException  {
		if(result.hasErrors()) {
			System.out.println("rossz");
			List<String> errorMessages = new ArrayList<>();
			result.getFieldErrors().forEach(e -> {
				errorMessages.add(e.getField() + " " + e.getDefaultMessage());
			});
			throw new WebshopException(errorMessages);
		}
		order = this.service.save(order);
		return order;
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return "Ok";
	}
	
	@GetMapping("/get/{id}")
	public Order getOne(@PathVariable("id") Long id) {
		Order order = service.getOne(id);
		return order;
	}


}
