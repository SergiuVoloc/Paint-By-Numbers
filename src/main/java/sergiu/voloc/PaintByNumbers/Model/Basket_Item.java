package sergiu.voloc.PaintByNumbers.Model;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity(name = "basket_item")
public class Basket_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int quantity;

// Relationships
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_details_id")
    private Product_Details product_details;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Basket_Item() {
    }

    public Basket_Item(UUID id, UUID user_id, UUID product_details_id, UUID product_id, int quantity, Product product, Product_Details product_details, User user) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.product_details = product_details;
        this.user = user;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product_Details getProduct_details() {
        return product_details;
    }

    public void setProduct_details(Product_Details product_details) {
        this.product_details = product_details;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
