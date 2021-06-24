package com.ecommerce.ecommerce.modules.pbn;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PBNRepository extends CrudRepository<PBN, UUID> {
    List<PBN> findByUserId(UUID userId);
}
