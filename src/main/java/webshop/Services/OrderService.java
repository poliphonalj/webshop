package webshop.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webshop.DTOs.OrderDTO;
import webshop.DTOs.SumOfItemsForADeliveryDAyDTO;
import webshop.Model.Order.Order;
import webshop.Model.Order.OrderItem;
import webshop.Repository.OrderItemRepo;
import webshop.Repository.OrderRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


//if status=false as basic  akkor nincs meg kiszallitva
@Service
public class OrderService {
    @PersistenceContext
    EntityManager em;

    @Autowired
    private OrderRepo orderRepo;
    private OrderItemRepo orderItemRepo;

    @Transactional
    public void setOrderStatusTrue(long ID) {
        Order order = this.getOne(ID);
        order.setStatus(true);
        orderRepo.save(order);
    }

    public List<OrderDTO> getOrdersPerDeliveryDay(long deliveryDayID) {
        List<Order> list = orderRepo.findOrdersByDeliveryDayID(deliveryDayID);
        return orderListToOrderDTOList(list);
    }

    @Transactional
    public List<OrderDTO> getAllUndelivered() {
        List<Order> list = this.orderRepo.findAllByStatusFalse();
        return orderListToOrderDTOList(list);
    }

    @Transactional
    public List<OrderDTO> getAll() {
        List<Order> list = this.orderRepo.findAll();
        return orderListToOrderDTOList(list);
    }

    @Transactional
    public HashMap<String, Long> getSumOfItemsForADeliveryDay(long deliveryDayID) {
        //em.createQuery("SELECT  orders.deliveryDayID FROM Order orders where ")
        //select name,sum quantity drom orderitem where orderid is in (select orderid from order where orderday=xx)
        List<OrderItem> list = (List<OrderItem>) em.createQuery("SELECT  orderItems FROM OrderItem orderItems where orderItems.order.deliveryDayID=:p Group By orderItems.name").
                setParameter("p", deliveryDayID).getResultList();

        List<SumOfItemsForADeliveryDAyDTO> listToReturn = new ArrayList<>();
HashMap<String,Long>hmap=new HashMap<>();
        for (OrderItem actualOrder : list) {
            System.out.println(actualOrder.getName().toString());
            System.out.println(actualOrder.getQuantity());
           hmap.putIfAbsent(actualOrder.getName(), actualOrder.getQuantity());

           hmap.put(actualOrder.getName(), hmap.get(actualOrder.getName()).longValue()+actualOrder.getQuantity());

        }
        return hmap;
    }


    @Transactional
    public List<OrderDTO> orderListToOrderDTOList(List<Order> list) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order actualOrder : list) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderTime(actualOrder.getOrderTime());
            orderDTO.setPaymentType(actualOrder.getPaymentType());
            orderDTO.setDeliveryFee(actualOrder.getDeliveryFee());

            orderDTO.setFirstName(actualOrder.getFirstName());
            orderDTO.setLastName(actualOrder.getLastName());
            orderDTO.setPhoneNumber(actualOrder.getPhoneNumber());
            orderDTO.setUsername(actualOrder.getUsername());
            orderDTO.setCity_delivery(actualOrder.getCity_delivery());
            orderDTO.setPostCode_delivery(actualOrder.getPostCode_delivery());
            orderDTO.setSimpleAddress_delivery(actualOrder.getSimpleAddress_delivery());
            orderDTO.setComment_delivery(actualOrder.getComment_delivery());
            orderDTO.setDeliveryDayID(actualOrder.getDeliveryDayID());
            orderDTO.setDeliveryGapsID(actualOrder.getDeliveryGapsID());

            orderDTO.setItemList(actualOrder.getOrdersItemList());
            List<OrderItem> itemList = actualOrder.getOrdersItemList();
            int totalProductPrice = 0;
            for (OrderItem actualItem : itemList) {
                totalProductPrice += actualItem.getPrice() * actualItem.getQuantity();
            }
            orderDTO.setTotalProductPrice(totalProductPrice);
            orderDTO.setTotalPrice(totalProductPrice + orderDTO.getDeliveryFee());
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Transactional
    public Order save(Order order) {
        order.setOrderTime(LocalDateTime.now());
        List<OrderItem> list = new ArrayList<>();
        list = order.getOrdersItemList();
        for (OrderItem actualOrderItem : list) {
            actualOrderItem.setOrder(order);
            actualOrderItem.setProductID(actualOrderItem.getProductID());
        }
        return orderRepo.save(order);
    }

    @Transactional
    public void delete(long id) {
        orderRepo.deleteById(id);
    }

    public Order getOne(long id) {
        Optional<Order> opt = orderRepo.findById(id);
        return opt.isPresent() ? opt.get() : null;
    }
}
