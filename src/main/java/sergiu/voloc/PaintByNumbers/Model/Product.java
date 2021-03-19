package sergiu.voloc.PaintByNumbers.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String Name;
    private String Description;
    private Float Price;

    //    Setting up photo storage for products
    @Lob                                                //Large object
    @Type(type = "org.hibernate.type.BinaryType")       //handle binary data
    private byte[] product_photo;


    //    <--- Relationship --->
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",
                    referencedColumnName = "id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "id")
    private List<Product_Photo> productPhotos = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Basket_Item> basket_item_List = new ArrayList<>();



    public Product() {
    }

    public Product(UUID id, String name, String description, Float price, byte[] product_photo,
                   List<Category> categories, List<Product_Photo> productPhotos) {
        this.id = id;
        Name = name;
        Description = description;
        Price = price;
        this.product_photo = product_photo;
        this.categories = categories;
        this.productPhotos = productPhotos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public byte[] getProduct_photo() {
        return product_photo;
    }

    public void setProduct_photo(byte[] product_photo) {
        this.product_photo = product_photo;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Product_Photo> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<Product_Photo> productPhotos) {
        this.productPhotos = productPhotos;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void addPhoto(Product_Photo file){
        this.productPhotos.add(file);
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + Name + '\'' +
                ", description='" + Description + '\'' +
                ", price=" + Price +
                ", categories=" + categories +
                '}';
    }
}
