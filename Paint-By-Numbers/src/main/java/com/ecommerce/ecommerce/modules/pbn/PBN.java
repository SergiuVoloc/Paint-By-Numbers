package com.ecommerce.ecommerce.modules.pbn;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import com.ecommerce.ecommerce.modules.user.User;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Indexed
public class PBN {
    //    <--- Properties --->
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Field
    private String name;

    @Field
    private Date created_at;

    //    <--- Relationship --->
    @OneToMany(
            targetEntity = FileStorage.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @IndexedEmbedded(depth=1)
    private List<FileStorage> files = new ArrayList<>();

    @ManyToOne
    private User user;

    public PBN(UUID id, User user) {
        this.id = id;
        this.user = user;
        this.created_at = new Date();
    }

    public PBN() {
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFile(FileStorage file) { this.files.add(file); }
    public void removeFile(FileStorage file) { this.files.remove(file); }
    public List<FileStorage> getFiles() {
        return files;
    }
    public void setFiles(List<FileStorage> files) {
        this.files = files;
    }

    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
