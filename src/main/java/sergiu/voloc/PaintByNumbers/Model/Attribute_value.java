package sergiu.voloc.PaintByNumbers.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "attribute_values")
public class Attribute_value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long attribute_id;
    private long entity_id;
    private String value;


    public Attribute_value() {
    }

    public Attribute_value(long id, long attribute_id, long entity_id, String value) {
        this.id = id;
        this.attribute_id = attribute_id;
        this.entity_id = entity_id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(long attribute_id) {
        this.attribute_id = attribute_id;
    }

    public long getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(long entity_id) {
        this.entity_id = entity_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
