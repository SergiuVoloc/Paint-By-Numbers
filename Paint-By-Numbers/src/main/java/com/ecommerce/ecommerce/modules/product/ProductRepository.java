package com.ecommerce.ecommerce.modules.product;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
    List<Product> findAllByFiles(FileStorage f);
}
