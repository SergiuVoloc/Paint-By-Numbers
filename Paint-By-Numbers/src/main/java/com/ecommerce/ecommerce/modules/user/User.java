package com.ecommerce.ecommerce.modules.user;

import com.ecommerce.ecommerce.modules.address.Address;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user_app")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique=true)
    private String username;

    private String password, fullName, phone, role;
    private Date dateOfBirth;
    private Boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    public User(){}

    public User(String username, String password, String fullName, String phone, String role, Date dateOfBirth, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.enabled = enabled;
    }

    public User(UUID id, String username, String password, String fullName, String phone, String role, Date dateOfBirth, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.enabled = enabled;
    }

    public User(String username, String password, String fullName, String phone, Date dateOfBirth) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    public void addAddress(Address item){
        this.addresses.add(item);
    }
    public void removeAddress(Address item){
        this.addresses.remove(item);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", enabled=" + enabled +
                ", addresses=" + addresses +
                '}';
    }
}

