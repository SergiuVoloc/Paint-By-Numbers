package sergiu.voloc.PaintByNumbers.Model;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "product_photos")
public class Product_Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID product_id;
    private String name;

    //    Setting up photo storage for products
    @Lob                                                //Large object
    @Type(type = "org.hibernate.type.BinaryType")       //handle binary data
    private byte[] photo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File_Storage> fileStorage = new ArrayList<>();


    public Product_Photo() {
    }

    public Product_Photo(UUID id, UUID product_id, String name, byte[] photo) {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.photo = photo;
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

    public List<File_Storage> getFileStorage() {
        return fileStorage;
    }

    public void setFileStorage(List<File_Storage> fileStorage) {
        this.fileStorage = fileStorage;
    }

    public void addPhoto(File_Storage fileStorage){
        this.fileStorage.add(fileStorage);
    }
}
