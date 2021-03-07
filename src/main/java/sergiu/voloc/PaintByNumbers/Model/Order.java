package sergiu.voloc.PaintByNumbers.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long user_id;
    private long address_id;
    private long discount_id;
    private long status_id;
    private Date created_at;
    private Date modified;
    private float shipping_cost;
    private float amount;
    private String order_note;


    public Order() {
    }

    public Order(long id, long user_id, long address_id, long discount_id, long status_id, Date created_at, Date modified, float shipping_cost, float amount, String order_note) {
        this.id = id;
        this.user_id = user_id;
        this.address_id = address_id;
        this.discount_id = discount_id;
        this.status_id = status_id;
        this.created_at = created_at;
        this.modified = modified;
        this.shipping_cost = shipping_cost;
        this.amount = amount;
        this.order_note = order_note;
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

    public long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(long address_id) {
        this.address_id = address_id;
    }

    public long getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(long discount_id) {
        this.discount_id = discount_id;
    }

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public float getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(float shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }
}
