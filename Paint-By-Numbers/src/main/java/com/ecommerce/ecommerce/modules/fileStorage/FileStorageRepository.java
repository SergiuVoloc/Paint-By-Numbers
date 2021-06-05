package com.ecommerce.ecommerce.modules.fileStorage;

import com.ecommerce.ecommerce.modules.product.Product;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.UUID;

public interface FileStorageRepository extends CrudRepository<FileStorage, UUID> {
    FileStorage findByName(String f);
    FileStorage findByInternalName(String f);
}
