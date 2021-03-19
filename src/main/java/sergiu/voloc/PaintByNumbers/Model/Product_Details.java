package sergiu.voloc.PaintByNumbers.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "product_details")
public class Product_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID product_id;
    private String size;

    @OneToMany(mappedBy = "product_details")
    private List<Basket_Item> basket_item_List = new ArrayList<>();


    public Product_Details() {
    }

    public Product_Details(UUID id, UUID product_id, String size) {
        this.id = id;
        this.product_id = product_id;
        this.size = size;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProduct_id() {
        return product_id;
    }

    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
