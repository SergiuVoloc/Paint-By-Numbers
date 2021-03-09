package sergiu.voloc.PaintByNumbers.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String full_name;
    private Date date_of_birth;
    private String phone;
    private String password;
    private Timestamp created_at;
    private boolean deleted;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="address_id")
    private Address address;


    public User() {
    }

    public User(long id, String email, String full_name, Date date_of_birth, String phone, String password, Timestamp created_at, Boolean deleted) {
        this.id = id;
        this.email = email;
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.phone = phone;
        this.password = password;
        this.created_at = created_at;
        this.deleted = deleted;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



}