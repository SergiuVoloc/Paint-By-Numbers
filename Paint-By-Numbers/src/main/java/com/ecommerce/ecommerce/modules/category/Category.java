package com.ecommerce.ecommerce.modules.category;

import com.ecommerce.ecommerce.modules.product.Product;

import javax.persistence.*;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@Entity
public class Category {

//    <--- Properties --->
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name, slug;



//    <--- Relationship --->
    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    public Category(String name) {
        this.name = name;
        this.slug = toSlug(name);
    }
    public Category(){}

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
        this.slug = toSlug(name);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String toSlug(String input) {
        final Pattern NONLATIN = Pattern.compile("[^\\w-]");
        final Pattern WHITESPACE = Pattern.compile("[\\s]");
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
