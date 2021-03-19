package sergiu.voloc.PaintByNumbers.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "address",
                cascade = {CascadeType.DETACH, CascadeType.MERGE,
                           CascadeType.PERSIST, CascadeType.REFRESH})
    private List<User> userList = new ArrayList<>();

    private UUID user_id;
    private String full_name;
    private String address1;
    private String address2;
    private String postcode;
    private String phone;



    // to review code below, maybe no need because of "= new ArrayList<>()" on line 18

    // convenience methods for bi-directional relationship
//    public void add(User tempUser){
//        if(userList = null) {
//            userList = new ArrayList<>();
//        }
//            userList.add(tempUser);
//
//            tempUser.setAddress(this);
//    }


    public Address() {
    }

    public Address(UUID id, UUID user_id, String full_name, String address1, String address2, String postcode, String phone) {
        this.id = id;
        this.user_id = user_id;
        this.full_name = full_name;
        this.address1 = address1;
        this.address2 = address2;
        this.postcode = postcode;
        this.phone = phone;
    }




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
