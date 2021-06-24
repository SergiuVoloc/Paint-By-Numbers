package com.ecommerce.ecommerce.modules.fileStorage;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Indexed
public class  FileStorage {

//    <--- Properties --->
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Field
    private String name, internalName, details, path, public_path, mime_type, extension;
    @Field
    private Long size;

    public FileStorage(String name,String internalName, String details, String path, String public_path, String mime_type, String extension, Long size) {
        this.name = name;
        this.internalName = internalName;
        this.details = details;
        this.path = path;
        this.public_path = public_path;
        this.mime_type = mime_type;
        this.extension = extension;
        this.size = size;
    }

    public FileStorage(){}

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPublic_path() {
        return public_path;
    }

    public void setPublic_path(String public_path) {
        this.public_path = public_path;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    @Override
    public String toString() {
        return "FileStorage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", internalName='" + internalName + '\'' +
                ", details='" + details + '\'' +
                ", path='" + path + '\'' +
                ", public_path='" + public_path + '\'' +
                ", mime_type='" + mime_type + '\'' +
                ", extension='" + extension + '\'' +
                ", size=" + size +
                '}';
    }
}
