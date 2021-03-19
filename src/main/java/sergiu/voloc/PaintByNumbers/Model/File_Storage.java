package sergiu.voloc.PaintByNumbers.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "file_storage")
public class File_Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID product_id;
    private String name;
    private String value;
    private String details;
    private String path;
    private String mime_type;
    private String extension;
    private Long size;


    public File_Storage() {
    }

    public File_Storage(UUID id, UUID product_id, String name, String value, String details, String path,
                        String mime_type, String extension, Long size) {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.value = value;
        this.details = details;
        this.path = path;
        this.mime_type = mime_type;
        this.extension = extension;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileStorage{" +
                "id=" + id +
                "product_id=" + product_id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", path='" + path + '\'' +
                ", mime_type='" + mime_type + '\'' +
                ", extension='" + extension + '\'' +
                ", size=" + size +
                '}';
    }
}
