package com.ecommerce.ecommerce.modules.value;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ValueRepository extends CrudRepository<Value, UUID> {
}