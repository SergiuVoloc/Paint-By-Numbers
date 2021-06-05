package com.ecommerce.ecommerce.modules.discount;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DiscountRepository extends CrudRepository<Discount, UUID> {
    boolean existsByName(String s);
    Discount findByName(String s);
}
