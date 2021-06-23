package com.ecommerce.ecommerce.modules.product;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

// in order to use pagination, RestRepository was changed to JpaRepository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByFiles(FileStorage f);
}
