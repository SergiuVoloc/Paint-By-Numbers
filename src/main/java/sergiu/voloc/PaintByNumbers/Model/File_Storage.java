package sergiu.voloc.PaintByNumbers.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "file_storage")
public class File_Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long product_id;
    private String name;
    private String value;


    public File_Storage() {
    }

    public File_Storage(long id, long product_id, String name, String value) {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
