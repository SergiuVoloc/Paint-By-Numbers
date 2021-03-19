package sergiu.voloc.PaintByNumbers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sergiu.voloc.PaintByNumbers.Model.Basket_Item;
import sergiu.voloc.PaintByNumbers.Model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface BasketItemRepository extends JpaRepository<Basket_Item, UUID> {

    public List<Basket_Item> findByUser(User user);

}
