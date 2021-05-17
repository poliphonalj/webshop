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
import webshop.Model.Orders.Orders;
import webshop.Model.Orders.OrderItem;
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
    private DeliveryService deliveryService;

    @Transactional
    public void setOrderStatusTrue(long ID) {
        Orders orders = this.getOne(ID);
        orders.setStatus(true);
        orderRepo.save(orders);
    }

    public List<OrderDTO> getOrdersPerDeliveryDay(long deliveryDayID) {
        List<Orders> list = orderRepo.findOrdersByDeliveryDayID(deliveryDayID);
        return orderListToOrderDTOList(list);
    }

    @Transactional
    public List<OrderDTO> getAllUndelivered() {
        List<Orders> list = this.orderRepo.findAllByStatusFalse();
        return orderListToOrderDTOList(list);
    }

    @Transactional
    public List<OrderDTO> getAll() {
        List<Orders> list = this.orderRepo.findAll();
        return orderListToOrderDTOList(list);
    }

    //:)
    @Transactional
    public HashMap<String, Long> getSumOfItemsForADeliveryDay(long deliveryDayID) {
        //em.createQuery("SELECT  orders.deliveryDayID FROM Order orders where ")
        //select name,sum quantity drom orderitem where orderid is in (select orderid from order where orderday=xx)
        List<OrderItem> list = (List<OrderItem>) em.createQuery("SELECT  orderItems FROM OrderItem orderItems where orderItems.order.deliveryDayID=:p").
                setParameter("p", deliveryDayID).getResultList();

        HashMap<String, Long> hmap = new HashMap<>();
        for (OrderItem actualOrder : list) {


            if (hmap.containsKey(actualOrder.getName())) {
                hmap.put(actualOrder.getName(), hmap.get(actualOrder.getName())+ actualOrder.getQuantity());
            } else {
                hmap.put(actualOrder.getName(), actualOrder.getQuantity());
            }
           // hmap.putIfAbsent(actualOrder.getName(), actualOrder.getQuantity());
           //hmap.put(actualOrder.getName(),hmap.get(actualOrder.getName())+actualOrder.getQuantity());
        }
        return hmap;
    }
@Transactional
    public List<Orders>getDeliveryOrdersByPostCode(long deliveryDayID){
    List<Orders> list = (List<Orders>) em.createQuery("SELECT  order FROM order order where order.deliveryDayID=:p ORDER BY order.postCode_delivery").
            setParameter("p", deliveryDayID).getResultList();
   return list;
    }
//itt torik el
    @Transactional
    public List<Orders>getDeliveryOrdersByDeliveryGaps(long deliveryDayID){
        List<Orders> list = (List<Orders>) em.createQuery("SELECT  orders " +
                " FROM orders orders "+
                "where orders.deliveryDayID=:p  ").
                setParameter("p", deliveryDayID).getResultList();
        return list;
    }

    @Transactional
    public List<OrderDTO> orderListToOrderDTOList(List<Orders> list) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Orders actualOrders : list) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setID(actualOrders.getID());
            orderDTO.setOrderTime(actualOrders.getOrderTime());
            orderDTO.setPaymentType(actualOrders.getPaymentType());
            orderDTO.setDeliveryFee(actualOrders.getDeliveryFee());

            orderDTO.setFirstName(actualOrders.getFirstName());
            orderDTO.setLastName(actualOrders.getLastName());
            orderDTO.setPhoneNumber(actualOrders.getPhoneNumber());
            orderDTO.setUsername(actualOrders.getUsername());
            orderDTO.setCity_delivery(actualOrders.getCity_delivery());
            orderDTO.setPostCode_delivery(actualOrders.getPostCode_delivery());
            orderDTO.setSimpleAddress_delivery(actualOrders.getSimpleAddress_delivery());
            orderDTO.setComment_delivery(actualOrders.getComment_delivery());
            orderDTO.setDeliveryDayID(actualOrders.getDeliveryDayID());
            orderDTO.setDeliveryGapsID(actualOrders.getDeliveryGapsID());

            orderDTO.setItemList(actualOrders.getOrdersItemList());
            List<OrderItem> itemList = actualOrders.getOrdersItemList();
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
    public Orders save(Orders orders) {
        orders.setOrderTime(LocalDateTime.now());

        //deliveryService.book(order.getDeliveryDayID(),order.getDeliveryGapsID());
        List<OrderItem> list = new ArrayList<>();
        list = orders.getOrdersItemList();

        for (OrderItem actualOrderItem : list) {
            actualOrderItem.setOrder(orders);
            actualOrderItem.setProductID(actualOrderItem.getProductID());
        }
        return orderRepo.save(orders);
    }

    @Transactional
    public void delete(long id) {
        orderRepo.deleteById(id);
    }

    public Orders getOne(long id) {
        Optional<Orders> opt = orderRepo.findById(id);
        return opt.isPresent() ? opt.get() : null;
    }
}