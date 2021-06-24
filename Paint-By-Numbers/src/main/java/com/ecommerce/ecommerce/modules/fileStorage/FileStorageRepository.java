package com.ecommerce.ecommerce.modules.fileStorage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileStorageRepository extends CrudRepository<FileStorage, UUID> {
    FileStorage findByName(String f);
    FileStorage findByInternalName(String f);
}
