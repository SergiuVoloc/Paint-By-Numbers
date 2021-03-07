package sergiu.voloc.PaintByNumbers.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity(name = "basket_item")
public class Basket_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long user_id;
    private long product_details_id;
    private boolean saved_for_later;
    private int quantity;
    private Date time_added;

    public Basket_Item() {
    }

    public Basket_Item(long id, long user_id, long product_details_id, boolean saved_for_later, int quantity, Date time_added) {
        this.id = id;
        this.user_id = user_id;
        this.product_details_id = product_details_id;
        this.saved_for_later = saved_for_later;
        this.quantity = quantity;
        this.time_added = time_added;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getProduct_details_id() {
        return product_details_id;
    }

    public void setProduct_details_id(long product_details_id) {
        this.product_details_id = product_details_id;
    }

    public boolean isSaved_for_later() {
        return saved_for_later;
    }

    public void setSaved_for_later(boolean saved_for_later) {
        this.saved_for_later = saved_for_later;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getTime_added() {
        return time_added;
    }

    public void setTime_added(Date time_added) {
        this.time_added = time_added;
    }
}
