package sergiu.voloc.PaintByNumbers.Repository;

import sergiu.voloc.PaintByNumbers.Model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
}
