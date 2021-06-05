package com.ecommerce.ecommerce.modules.address;

import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
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

    public void create(Address a){
        addressRepository.save(a);
    }
    public Address update(
            @PathVariable(value = "id") UUID id,
            Address o
    ){
        Address c = addressRepository.findById(id).orElseThrow();
        addressRepository.save((Address) objectUtils.update(c,o));
        return c;
    }

    public void delete(@PathVariable(value = "id") UUID id){
        addressRepository.deleteById(id);
    }
}
