package sergiu.voloc.PaintByNumbers.Model;


import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "product_photos")
public class Product_Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long product_id;
    private String name;

    //    Setting up photo storage for products
    @Lob                                                //Large object
    @Type(type = "org.hibernate.type.BinaryType")       //handle binary data
    private byte[] photo;

    public Product_Photo() {
    }

    public Product_Photo(long id, long product_id, String name, byte[] photo) {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.photo = photo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
