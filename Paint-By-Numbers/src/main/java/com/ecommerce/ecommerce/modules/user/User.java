package com.ecommerce.ecommerce.modules.user;

import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name="user_app")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID id;

    @Column(name ="username", unique=true)
    private String username;

    @Length(min=5, message="Password should be at least 5 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "enabled")
    private Boolean enabled;


    public User(){}

    public User(String username, String password, String fullName, String email, String phone, String role,
                Date dateOfBirth,
                Boolean enabled) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.enabled = enabled;
        this.email = email;
    }

    public User(UUID id, String username, String password, String fullName, String email, String phone, String role, Date dateOfBirth, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.enabled = enabled;
        this.email = email;
    }

    public User(String username, String password, String fullName, String email, String phone, Date dateOfBirth) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", enabled=" + enabled +
                '}';
    }
}

