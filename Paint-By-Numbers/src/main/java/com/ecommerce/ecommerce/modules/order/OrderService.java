package com.ecommerce.ecommerce.modules.order;

import com.ecommerce.ecommerce.modules.address.Address;
import com.ecommerce.ecommerce.modules.address.AddressService;
import com.ecommerce.ecommerce.modules.cartItem.CartItem;
import com.ecommerce.ecommerce.modules.cartItem.CartItemService;
import com.ecommerce.ecommerce.modules.discount.Discount;
import com.ecommerce.ecommerce.modules.discount.DiscountService;
import com.ecommerce.ecommerce.modules.orderItem.OrderItem;
import com.ecommerce.ecommerce.modules.orderItem.OrderItemService;
import com.ecommerce.ecommerce.modules.orderStatus.OrderStatus;
import com.ecommerce.ecommerce.modules.orderStatus.OrderStatusService;
import com.ecommerce.ecommerce.modules.user.User;
import com.ecommerce.ecommerce.modules.user.UserService;
import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private UserService userService;
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
        User user = userService.getCurrent();
        List<CartItem> cartItems = cartItemService.findAllByUser();
        float amount = cartItemService.total() + delivery_fee;

        OrderStatus orderStatus = orderStatusService.read(UUID.fromString("c1bf0443-ff39-4403-a425-f444b1c15fd5"));
        Address address = addressService.read(UUIDAddress);
        Discount d = discountService.check(discount);
//        e1b4dcdd-e314-4f5b-a51e-3be0fb4b5aa8
//        55bc52f3-c60a-4004-a0e8-5f5be12c829d
        Order order = new Order(
                delivery_fee,
                amount,
                address,
                user,
                d,
                orderStatus
        );
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
