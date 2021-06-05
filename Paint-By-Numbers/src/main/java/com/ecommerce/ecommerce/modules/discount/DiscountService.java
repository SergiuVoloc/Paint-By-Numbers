package com.ecommerce.ecommerce.modules.discount;

import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.UUID;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private ObjectUtils objectUtils;

    public Iterable<Discount> all(){
        return discountRepository.findAll();
    }

    public Discount read(@PathVariable(value = "id") UUID id){
        return discountRepository.findById(id).orElseThrow();
    }

    public void create(Discount a){
        discountRepository.save(a);
    }
    public Discount update(
            @PathVariable(value = "id") UUID id,
            Discount o
    ){
        Discount c = discountRepository.findById(id).orElseThrow();
        discountRepository.save((Discount) objectUtils.update(c,o));
        return c;
    }

    public void delete(@PathVariable(value = "id") UUID id){
        discountRepository.deleteById(id);
    }

    public Discount findByName(String s){
        return discountRepository.findByName(s);
    }

    public Discount check(String s){
        if (discountRepository.existsByName(s)){
            Discount d = discountRepository.findByName(s);
            if (d.getValid().after(new Date()))
                return d;
        }
        return null;
    }
}
