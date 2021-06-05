package com.ecommerce.ecommerce.modules.value;

import com.ecommerce.ecommerce.modules.attribute.Attribute;
import com.ecommerce.ecommerce.modules.product.Product;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Field
    private String value;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id")
    @ContainedIn
    private Attribute attribute;


    @ManyToOne
    @JoinColumn(name = "product_id")
    @ContainedIn
    private Product product;

    public Value(String value, Attribute attribute, Product product) {
        this.value = value;
        this.attribute = attribute;
        this.product = product;
    }
    public Value(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
