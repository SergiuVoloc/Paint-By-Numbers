package sergiu.voloc.PaintByNumbers.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "product_details_attribute_values ")
public class Product_details_attribute_value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long product_details_id;
    private long attribute_values_id;


    public Product_details_attribute_value() {
    }

    public Product_details_attribute_value(long id, long product_details_id, long attribute_values_id) {
        this.id = id;
        this.product_details_id = product_details_id;
        this.attribute_values_id = attribute_values_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProduct_details_id() {
        return product_details_id;
    }

    public void setProduct_details_id(long product_details_id) {
        this.product_details_id = product_details_id;
    }

    public long getAttribute_values_id() {
        return attribute_values_id;
    }

    public void setAttribute_values_id(long attribute_values_id) {
        this.attribute_values_id = attribute_values_id;
    }
}
