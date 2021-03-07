package sergiu.voloc.PaintByNumbers.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;


@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Name;
    private String Description;
    private String Size;
    private float Price;

    //    Setting up photo storage for products
    @Lob                                                //Large object
    @Type(type = "org.hibernate.type.BinaryType")       //handle binary data
    private byte[] product_photo;




    //    Setting up Many to Many Relationship with Session
    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    private List<Session> sessions;


    //    Setting up Many to Many Relationship with Speaker
    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers;

}
