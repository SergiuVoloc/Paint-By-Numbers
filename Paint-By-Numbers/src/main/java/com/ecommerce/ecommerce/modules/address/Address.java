package com.ecommerce.ecommerce.modules.address;

import com.ecommerce.ecommerce.modules.user.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String fullName, address1, address2, postCode, phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(){}

    public Address(String fullName, String address1, String address2, String postCode, String phone) {
        this.fullName = fullName;
        this.address1 = address1;
        this.address2 = address2;
        this.postCode = postCode;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String full_name) {
        this.fullName = full_name;
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postcode) {
        this.postCode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", full_name='" + fullName + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", postcode='" + postCode + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
