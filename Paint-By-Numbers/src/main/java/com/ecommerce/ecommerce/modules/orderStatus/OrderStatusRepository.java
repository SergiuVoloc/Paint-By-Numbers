package com.ecommerce.ecommerce.modules.orderStatus;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, UUID> {
}
