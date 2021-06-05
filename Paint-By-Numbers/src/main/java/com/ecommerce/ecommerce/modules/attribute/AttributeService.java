package com.ecommerce.ecommerce.modules.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    public Iterable<Attribute> all(){
        return attributeRepository.findAll();
    }

    public Attribute read(@PathVariable(value = "id") UUID id){
        return attributeRepository.findById(id).orElseThrow();
    }

    public Attribute create(String name){
        Attribute n = new Attribute(name);
        attributeRepository.save(n);
        return n;
    }
    public Attribute update(@PathVariable(value = "id") UUID id, Attribute o){
        Attribute c = attributeRepository.findById(id).orElseThrow();
        c.setName(o.getName());
        attributeRepository.save(c);
        return c;
    }

    public void delete(@PathVariable(value = "id") UUID id){
        attributeRepository.deleteById(id);
    }

}
