package sergiu.voloc.PaintByNumbers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import sergiu.voloc.PaintByNumbers.Model.Basket_Item;
import sergiu.voloc.PaintByNumbers.Model.Product;
import sergiu.voloc.PaintByNumbers.Model.Product_Details;
import sergiu.voloc.PaintByNumbers.Model.User;
import sergiu.voloc.PaintByNumbers.Repository.BasketItemRepository;

import javax.persistence.EntityManager;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BasketTest {

    @Autowired
    private BasketItemRepository basketItemRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testAddOneBasketItem(){
        Product product =  entityManager.find(Product.class, "5833abf8-fc29-476f-b343-571839ded42f");

        Product_Details product_details = entityManager.find(Product_Details.class, "3290935b-4d23-4fc7-a3d7-5b221999d88f");

        User user = entityManager.find(User.class, "c2e800c9-8c54-47e6-999d-0e740367d967");

        Basket_Item newItem = new Basket_Item();
        newItem.setProduct(product);
        newItem.setProduct_details(product_details);
        newItem.setUser(user);
        newItem.setQuantity(1);

        Basket_Item saved_basket_item = basketItemRepository.save(newItem);

        assertTrue(saved_basket_item.getQuantity() > 1 );

    }
}
