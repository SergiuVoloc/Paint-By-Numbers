package sergiu.voloc.PaintByNumbers.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "attribute_values")
public class Attribute_value {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID attribute_id;
    private UUID entity_id;
    private String value;


    public Attribute_value() {
    }

    public Attribute_value(UUID id, UUID attribute_id, UUID entity_id, String value) {
        this.id = id;
        this.attribute_id = attribute_id;
        this.entity_id = entity_id;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(UUID attribute_id) {
        this.attribute_id = attribute_id;
    }

    public UUID getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(UUID entity_id) {
        this.entity_id = entity_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
