package webshop.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webshop.DTOs.OrderDTO;
import webshop.DTOs.SumOfItemsForADeliveryDAyDTO;
import webshop.Model.FeedbackToFrontend;
import webshop.Model.Orders.Orders;
import webshop.Services.DeliveryService;
import webshop.Services.OrderService;
import webshop.exception.WebshopException;

@RestController
@RequestMapping("/orders")
public class OrderController {

    OrderService service;

    DeliveryService deliveryService;

    SumOfItemsForADeliveryDAyDTO sum;

    @Autowired
    public OrderController(OrderService service, DeliveryService deliveryService) {
        this.service = service;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/get/undelivered")
    public ResponseEntity<?> getUnderlivered() {
        List<OrderDTO> list = this.service.getAllUndelivered();
        if (list != null) {
            HashMap<String, List<OrderDTO>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }

    //ezt akar automatikusan is lehet hivni es emailben is megkerni az eredmenyt
    @GetMapping("/getOrdersPerDeliveryDay/{deliveryDayID}")
    public ResponseEntity<?> getPerDeliveryDay(@PathVariable long deliveryDayID) {

        List<OrderDTO> list = service.getOrdersPerDeliveryDay(deliveryDayID);
        if (list != null) {
            HashMap<String, List<OrderDTO>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }

    @GetMapping("/getDeliveryOrderByPostCode/{deliveryDayID}")
    public ResponseEntity<?> getDeliveryOrderByPostCode(@PathVariable Long deliveryDayID) {
        List<Orders> list = service.getDeliveryOrdersByPostCode(deliveryDayID);
        if (!list.isEmpty()) {
            HashMap<String, List<Orders>> map = new HashMap<>();
            map.put("list", list);
            return ResponseEntity.ok().body(map);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));

    }

    @GetMapping("/getDeliveryOrdersByDeliveryGaps/{deliveryDayID}")
    public ResponseEntity<?> getDeliveryOrdersByDeliveryGaps(@PathVariable long deliveryDayID) {
        List<Orders> list = service.getDeliveryOrdersByDeliveryGaps(deliveryDayID);
        if (!list.isEmpty()) {
            HashMap<String, List<Orders>> map = new HashMap<>();
            map.put("list", list);
            return ResponseEntity.ok().body(map);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));

    }

    @GetMapping("/getSumPerDeliveryDay/{deliveryDayID}")
    public ResponseEntity<?> getSumOfItemsPerDeliveryDay(@PathVariable Long deliveryDayID) {

        HashMap<String, Long> hmap = service.getSumOfItemsForADeliveryDay(deliveryDayID);
        if (hmap != null) {
            HashMap<String, HashMap<String, Long>> map = new HashMap<>();
            map.put("list", hmap);
            return ResponseEntity.ok().body(map);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
    }


    @PostMapping("/setStatusTrue/{ID}")
    public ResponseEntity<?> setStatusFree(@PathVariable Long ID) {
        try {
            service.setOrderStatusTrue(ID);
            return ResponseEntity.ok(new FeedbackToFrontend(true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        List<OrderDTO> list = this.service.getAll();
        if (list != null) {
            HashMap<String, List<OrderDTO>> hMap = new HashMap<>();
            hMap.put("list", list);
            return ResponseEntity.ok().body(hMap);
        }
        return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));


    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Orders orders, BindingResult result) throws WebshopException {
        if (result.hasErrors()) {
            System.out.println("rossz");
            List<String> errorMessages = new ArrayList<>();
            result.getFieldErrors().forEach(e -> {
                errorMessages.add(e.getField() + " " + e.getDefaultMessage());
            });
            throw new WebshopException(errorMessages);
            //return ResponseEntity.badRequest().body(new FeedbackToFrontend(false));
        }
        orders = this.service.save(orders);

        deliveryService.book(orders.getDeliveryDayID(), orders.getDeliveryGapsID());////////////////////////////

        long orderID = orders.getID() + 1000;//kamubol tobbet mutasson
        String IDString = orderID + "";
        HashMap<String, String> hmap = new HashMap<>();
        hmap.put("successful", "true");
        hmap.put("ID", IDString);
        return ResponseEntity.ok(hmap);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "Ok";
    }

    @GetMapping("/get/{id}")
    public Orders getOne(@PathVariable("id") Long id) {
        Orders orders = service.getOne(id);
        return orders;
    }


}