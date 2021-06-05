package com.ecommerce.ecommerce.modules.product;

import com.ecommerce.ecommerce.modules.category.Category;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import com.ecommerce.ecommerce.modules.value.Value;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Indexed
public class Product {
    //    <--- Properties --->
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Field
    private String name;

    @Field
    @Column(columnDefinition="TEXT")
    private String description;
    @Field
    private float price;
    @Field
    private Date created_at;

    //    <--- Relationship --->
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @IndexedEmbedded(depth=1)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(
            targetEntity = FileStorage.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @IndexedEmbedded(depth=1)
    private List<FileStorage> photos = new ArrayList<>();

    @OneToMany(
            targetEntity = FileStorage.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @IndexedEmbedded(depth=1)
    private List<FileStorage> files = new ArrayList<>();

    @OneToMany(
            targetEntity = Value.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @IndexedEmbedded(depth=1)
    private List<Value> attributes = new ArrayList<>();

    public Product(UUID id, String name, String description, Float price, List<Category> categories, List<FileStorage> photos, List<Value> attributes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.attributes = attributes;
        this.photos = photos;
        this.created_at = new Date();
    }

    public Product() {
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
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public void addCategory(Category category) {
        this.categories.add(category);
    }
    public List<FileStorage> getPhotos() {
        return photos;
    }
    public void setPhotos(List<FileStorage> photos) {
        this.photos = photos;
    }
    public void addPhoto(FileStorage file) {
        this.photos.add(file);
    }
    public void removePhoto(FileStorage file) {
        this.photos.remove(file);
    }
    public void addFile(FileStorage file) { this.files.add(file); }
    public void removeFile(FileStorage file) { this.files.remove(file); }
    public List<FileStorage> getFiles() {
        return files;
    }
    public void setFiles(List<FileStorage> files) {
        this.files = files;
    }
    public List<Value> getAttributes() {
        return attributes;
    }
    public void setAttributes(List<Value> attributes) {
        this.attributes = attributes;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public String getCategories2String(){
        String r = "[";
        for (Category c: this.categories) {
            r += "\""+c.getSlug()+ "\",";
        }
        return r.substring(0,r.length()-1) + "]";
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                ", photos=" + photos +
                ", attributes=" + attributes +
                '}';
    }
}
