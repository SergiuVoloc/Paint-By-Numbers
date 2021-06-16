package com.ecommerce.ecommerce.modules.address;

import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ObjectUtils objectUtils;


    public List<Address> findAll(){
        return addressRepository.findAllByOrderByAddress1Asc();
    }

    public Address findById(UUID theId) {
        Optional<Address> result = addressRepository.findById(theId);

        Address theAddress = null;

        if(result.isPresent()){
            theAddress = result.get();
        }else{
            throw new RuntimeException("Did not find emlpoyee id: " + theId);
        }
        return theAddress;
    }


//    public Address findById(@PathVariable(value = "id") UUID id){
//        return addressRepository.findById(id).orElseThrow();
//    }


    public void save(Address newAddress) {
        addressRepository.save(newAddress);
    }


    public Address create(
            String full_name, String address1, String address2, String postcode, String phone)
    {
        Address newAddress = new Address(full_name,address1,address2,postcode,phone);
        addressRepository.save(newAddress);
        return newAddress;
    }


    public Address update(@PathVariable(value = "id") UUID id, Address o){
        Address c = addressRepository.findById(id).orElseThrow();
        c.setFullName(o.getFullName());
        c.setAddress1(o.getAddress1());
        c.setAddress2(o.getAddress2());
        c.setPhone(o.getPhone());
        c.setPostCode(o.getPostCode());
        addressRepository.save(c);
        return c;
    }


    public void delete(@PathVariable(value = "id") UUID id){
        addressRepository.deleteById(id);
    }


}
