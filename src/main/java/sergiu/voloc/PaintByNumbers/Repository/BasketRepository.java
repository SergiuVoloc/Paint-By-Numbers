package sergiu.voloc.PaintByNumbers.Repository;

import sergiu.voloc.PaintByNumbers.Model.Basket_Item;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BasketRepository extends CrudRepository<Basket_Item, UUID> {
}
