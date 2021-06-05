package com.ecommerce.ecommerce.modules.attribute;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AttributeRepository extends CrudRepository<Attribute, UUID> {
}