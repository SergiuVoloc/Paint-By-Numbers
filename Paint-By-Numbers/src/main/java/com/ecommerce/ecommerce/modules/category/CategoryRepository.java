package com.ecommerce.ecommerce.modules.category;

import com.ecommerce.ecommerce.modules.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category getCategoryBySlug(String s);
}