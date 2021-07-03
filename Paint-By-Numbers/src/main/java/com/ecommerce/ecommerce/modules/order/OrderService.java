package com.ecommerce.ecommerce.modules.order;


import com.ecommerce.ecommerce.modules.cartItem.CartItem;
import com.ecommerce.ecommerce.modules.cartItem.CartItemService;
import com.ecommerce.ecommerce.modules.orderItem.OrderItem;
import com.ecommerce.ecommerce.modules.orderItem.OrderItemService;
import com.ecommerce.ecommerce.modules.user.User;
import com.ecommerce.ecommerce.modules.user.UserServiceImpl;
import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired

    private OrderItemService orderItemService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ObjectUtils objectUtils;

    public Iterable<Order> all(){
        return orderRepository.findAll();
    }

    public Order read(UUID id){
        return orderRepository.findById(id).orElseThrow();
    }

    public void create(
            float delivery_fee,
            UUID UUIDAddress,
            String discount
    ){
        User user = userServiceImpl.getCurrent();
        List<CartItem> cartItems = cartItemService.findAllByUser();
        float amount = cartItemService.total() + delivery_fee;


        Order order = new Order(
                delivery_fee,
                amount,
                user);
        cartItems.forEach((e) -> {
            OrderItem tempOI = orderItemService.create(new OrderItem(e.getQty(), e.getProduct().getPrice(), e.getSubtotal(), e.getProduct()));
            order.addItem(tempOI);
        });
        orderRepository.save(order);
        cartItemService.clear();
    }
    public Order update(UUID id, Order o){
        Order c = orderRepository.findById(id).orElseThrow();
        orderRepository.save((Order) objectUtils.update(c,o));
        return c;
    }

    public void delete(@PathVariable(value = "id") UUID id){
        orderRepository.deleteById(id);
    }
}
