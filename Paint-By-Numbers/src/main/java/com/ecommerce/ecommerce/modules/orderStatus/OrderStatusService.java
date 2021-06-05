package com.ecommerce.ecommerce.modules.orderStatus;

import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private ObjectUtils objectUtils;

    public Iterable<OrderStatus> all(){
        return orderStatusRepository.findAll();
    }

    public OrderStatus read(@PathVariable(value = "id") UUID id){
        return orderStatusRepository.findById(id).orElseThrow();
    }

    public void create(OrderStatus a){
        orderStatusRepository.save(a);
    }
    public OrderStatus update(
            @PathVariable(value = "id") UUID id,
            OrderStatus o
    ){
        OrderStatus c = orderStatusRepository.findById(id).orElseThrow();
        orderStatusRepository.save((OrderStatus) objectUtils.update(c,o));
        return c;
    }

    public void delete(@PathVariable(value = "id") UUID id){
        orderStatusRepository.deleteById(id);
    }
}
