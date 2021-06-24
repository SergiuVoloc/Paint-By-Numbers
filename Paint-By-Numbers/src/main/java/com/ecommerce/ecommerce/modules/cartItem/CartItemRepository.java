package com.ecommerce.ecommerce.modules.cartItem;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface CartItemRepository extends CrudRepository<CartItem, UUID> {
    ArrayList<CartItem> findAllByUser_Id(UUID u);
    CartItem findByProduct_IdAndUser_Id(UUID p, UUID u);
    CartItem findByPbn_IdAndUser_Id(UUID pbn, UUID u);
}
