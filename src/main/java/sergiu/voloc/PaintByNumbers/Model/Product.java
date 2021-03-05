package sergiu.voloc.PaintByNumbers.Model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_Id;
    private String Name;
    private String Description;
    private String Size;
    private float Price;

    //    Setting up photo storage for products
    @Lob                                                //Large object
    @Type(type = "org.hibernate.type.BinaryType")       //handle binary data
    private byte[] product_photo;
}
