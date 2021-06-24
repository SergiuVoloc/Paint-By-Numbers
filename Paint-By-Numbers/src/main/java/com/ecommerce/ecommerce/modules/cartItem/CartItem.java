package com.ecommerce.ecommerce.modules.cartItem;


import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import com.ecommerce.ecommerce.modules.pbn.PBN;
import com.ecommerce.ecommerce.modules.product.Product;
import com.ecommerce.ecommerce.modules.user.User;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int qty;
    private float subtotal;
    private Boolean frame;
    private String size;


    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @ManyToOne
    private PBN pbn;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public  CartItem(){}

    public CartItem(int qty, User user, Product product) {
        this.qty = qty;
        this.user = user;
        this.product = product;
        this.subtotal = this.product.getPrice() * this.qty;
    }
    public CartItem(int qty, User user, Product product, float subtotal) {
        this.qty = qty;
        this.user = user;
        this.product = product;
        this.subtotal = subtotal * this.qty;
    }
    public CartItem(int qty, User user, Product product, PBN pbn, float subtotal, Boolean frame, String size) {
        this.qty = qty;
        this.user = user;
        this.pbn = pbn;
        this.product = product;
        this.subtotal = subtotal * this.qty;
        this.size = size;
        this.frame = frame;
    }
    @OneToMany(
            targetEntity = FileStorage.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @IndexedEmbedded(depth=1)
    private List<FileStorage> files = new ArrayList<>();


    public PBN getPbn() {
        return pbn;
    }

    public void setPbn(PBN pbn) {
        this.pbn = pbn;
    }

    public void addFile(FileStorage file) { this.files.add(file); }

    public void removeFile(FileStorage file) { this.files.remove(file); }

    public List<FileStorage> getFiles() {
        return files;
    }
    public void setFiles(List<FileStorage> files) {
        this.files = files;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public float getSubtotal() {
        return (float) ((float)Math.round(this.subtotal * 100.0) / 100.0);
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }


    public Boolean getFrame() {
        return frame;
    }

    public void setFrame(Boolean frame) {
        this.frame = frame;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", qty=" + qty +
                ", subtotal=" + subtotal +
                ", frame=" + frame +
                ", size='" + size + '\'' +
                ", user=" + user +
                ", product=" + product +
                ", pbn=" + pbn +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", files=" + files +
                '}';
    }


    //    @PreUpdate
//    public void preUpdate(){
//        this.subtotal = this.product.getPrice()*this.qty ;
//    }
}
