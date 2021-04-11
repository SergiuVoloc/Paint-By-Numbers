package sergiu.voloc.PaintByNumbers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sergiu.voloc.PaintByNumbers.Model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {

    Iterable<Product> findByNameLike(String productName);

    @Query("SELECT name FROM products where name like %:keyword%")
    List<String> search(@Param("keyword") String keyword);
}
