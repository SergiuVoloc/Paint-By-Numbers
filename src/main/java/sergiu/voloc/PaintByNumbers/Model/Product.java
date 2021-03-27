package sergiu.voloc.PaintByNumbers.Model;

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
    private String name;
    private String description;
    private Float price;

    //    Setting up photo storage for products
//    @Lob                                                //Large object
//    @Type(type = "org.hibernate.type.BinaryType")       //handle binary data
//    private byte[] product_photo;


    //    <--- Relationship --->
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(targetEntity = File_Storage.class, cascade = CascadeType.ALL,
                fetch = FetchType.LAZY, orphanRemoval = true)
    private List<File_Storage> photos = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Basket_Item> basketItemList = new ArrayList<>();



    public Product() {
    }

    public Product(UUID id, String name, String description, Float price, byte[] product_photo,
                   List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

//    public byte[] getProduct_photo() {
//        return product_photo;
//    }
//
//    public void setProduct_photo(byte[] product_photo) {
//        this.product_photo = product_photo;
//    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void addPhoto(File_Storage file){
        this.photos.add(file);
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                '}';
    }
}
