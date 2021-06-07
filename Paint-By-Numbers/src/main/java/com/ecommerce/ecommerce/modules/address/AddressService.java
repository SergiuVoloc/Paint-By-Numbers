package com.ecommerce.ecommerce.modules.address;

import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ObjectUtils objectUtils;


    public Iterable<Address> all(){
        return addressRepository.findAll();
    }


    public Address read(@PathVariable(value = "id") UUID id){
        return addressRepository.findById(id).orElseThrow();
    }


    public Address create(
            String full_name,
            String address1,
            String address2,
            String postcode,
            String phone)
    {
        Address newAddress = new Address(full_name,address1,address2,postcode,phone);
        addressRepository.save(newAddress);
        return newAddress;
    }


    public Address update(
            @PathVariable(value = "id") UUID id,
            Address o){
        Address c = addressRepository.findById(id).orElseThrow();
        c.setFull_name(o.getFull_name());
        c.setAddress1(o.getAddress1());
        c.setAddress2(o.getAddress2());
        c.setPhone(o.getPhone());
        c.setPostcode(o.getPostcode());
        addressRepository.save(c);
        return c;
    }


    public void delete(@PathVariable(value = "id") UUID id){
        addressRepository.deleteById(id);
    }
}
