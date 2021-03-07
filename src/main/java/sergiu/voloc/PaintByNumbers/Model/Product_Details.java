package sergiu.voloc.PaintByNumbers.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "product_details")
public class Product_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long product_id;
    private String size;


    public Product_Details() {
    }

    public Product_Details(long id, long product_id, String size) {
        this.id = id;
        this.product_id = product_id;
        this.size = size;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
