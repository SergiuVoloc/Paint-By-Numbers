package sergiu.voloc.PaintByNumbers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sergiu.voloc.PaintByNumbers.Model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {

    Iterable<Product> findByNameLike(String productName);
}
