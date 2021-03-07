package sergiu.voloc.PaintByNumbers.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "order_item")
public class Order_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long order_id;
    private long product_details_id;
    private int quantity;






    public Order_Item() {
    }

    public Order_Item(long id, long order_id, long product_details_id, int quantity) {
        this.id = id;
        this.order_id = order_id;
        this.product_details_id = product_details_id;
        this.quantity = quantity;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getProduct_details_id() {
        return product_details_id;
    }

    public void setProduct_details_id(long product_details_id) {
        this.product_details_id = product_details_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
