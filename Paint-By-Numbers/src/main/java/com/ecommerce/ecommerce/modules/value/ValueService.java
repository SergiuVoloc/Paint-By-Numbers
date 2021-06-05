package com.ecommerce.ecommerce.modules.value;

import com.ecommerce.ecommerce.modules.attribute.Attribute;
import com.ecommerce.ecommerce.modules.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class ValueService {

    @Autowired
    private ValueRepository valueRepository;

    public Iterable<Value> all(){
        return valueRepository.findAll();
    }

    public Value read(@PathVariable(value = "id") UUID id){
        return valueRepository.findById(id).orElseThrow();
    }

    public Value create(String value, Attribute attribute, Product product){
        Value n = new Value(value, attribute, product);
        valueRepository.save(n);
        return n;
    }
    public Value update(@PathVariable(value = "id") UUID id, Value o){
        Value c = valueRepository.findById(id).orElseThrow();
        valueRepository.save(c);
        return c;
    }

    public void delete(@PathVariable(value = "id") UUID id){
        valueRepository.deleteById(id);
    }

}
