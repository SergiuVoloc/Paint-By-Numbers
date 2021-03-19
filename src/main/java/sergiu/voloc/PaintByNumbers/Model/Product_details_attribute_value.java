package sergiu.voloc.PaintByNumbers.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "product_details_attribute_values ")
public class Product_details_attribute_value {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID product_details_id;
    private UUID attribute_values_id;


    public Product_details_attribute_value() {
    }

    public Product_details_attribute_value(UUID id, UUID product_details_id, UUID attribute_values_id) {
        this.id = id;
        this.product_details_id = product_details_id;
        this.attribute_values_id = attribute_values_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProduct_details_id() {
        return product_details_id;
    }

    public void setProduct_details_id(UUID product_details_id) {
        this.product_details_id = product_details_id;
    }

    public UUID getAttribute_values_id() {
        return attribute_values_id;
    }

    public void setAttribute_values_id(UUID attribute_values_id) {
        this.attribute_values_id = attribute_values_id;
    }
}
