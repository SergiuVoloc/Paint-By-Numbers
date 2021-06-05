package com.ecommerce.ecommerce.modules.slider;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import com.ecommerce.ecommerce.modules.product.Product;

import javax.persistence.*;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;

@Entity
public class Slider {

//    <--- Properties --->
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title, subtitle;

//    <--- Relationship --->
    @ManyToOne
    @JoinColumn(name = "image_id")
    private FileStorage image;


    public Slider() {}
    public Slider(String title, String subtitle, FileStorage image) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public FileStorage getImage() {
        return image;
    }

    public void setImage(FileStorage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Slider{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", image=" + image +
                '}';
    }
}
