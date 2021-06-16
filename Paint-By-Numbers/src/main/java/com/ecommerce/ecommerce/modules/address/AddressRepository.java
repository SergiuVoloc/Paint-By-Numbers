package com.ecommerce.ecommerce.modules.address;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends CrudRepository<Address, UUID> {

    // custom method to find all addresses and sort them ascending using Spring Data JPA
    public List<Address> findAllByOrderByAddress1Asc();
}
