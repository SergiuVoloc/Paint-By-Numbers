package sergiu.voloc.PaintByNumbers.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "order_item")
public class Order_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID order_id;
    private UUID product_details_id;
    private int quantity;






    public Order_Item() {
    }

    public Order_Item(UUID id, UUID order_id, UUID product_details_id, int quantity) {
        this.id = id;
        this.order_id = order_id;
        this.product_details_id = product_details_id;
        this.quantity = quantity;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrder_id() {
        return order_id;
    }

    public void setOrder_id(UUID order_id) {
        this.order_id = order_id;
    }

    public UUID getProduct_details_id() {
        return product_details_id;
    }

    public void setProduct_details_id(UUID product_details_id) {
        this.product_details_id = product_details_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
