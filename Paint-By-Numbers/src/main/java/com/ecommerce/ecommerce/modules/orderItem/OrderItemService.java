package com.ecommerce.ecommerce.modules.orderItem;

import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ObjectUtils objectUtils;

    public Iterable<OrderItem> all(){
        return orderItemRepository.findAll();
    }

    public OrderItem read(@PathVariable(value = "id") UUID id){
        return orderItemRepository.findById(id).orElseThrow();
    }

    public OrderItem create(OrderItem a){
        return  orderItemRepository.save(a);
    }
    public OrderItem update(
            @PathVariable(value = "id") UUID id,
            OrderItem o
    ){
        OrderItem c = orderItemRepository.findById(id).orElseThrow();
        orderItemRepository.save((OrderItem) objectUtils.update(c,o));
        return c;
    }

    public void delete(@PathVariable(value = "id") UUID id){
        orderItemRepository.deleteById(id);
    }
}
