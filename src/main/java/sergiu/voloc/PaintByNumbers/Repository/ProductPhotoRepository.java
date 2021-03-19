package sergiu.voloc.PaintByNumbers.Repository;

import sergiu.voloc.PaintByNumbers.Model.Product_Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductPhotoRepository extends CrudRepository<Product_Photo, UUID> {
}
