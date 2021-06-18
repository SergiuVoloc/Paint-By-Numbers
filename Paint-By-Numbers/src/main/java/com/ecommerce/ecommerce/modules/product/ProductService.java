package com.ecommerce.ecommerce.modules.product;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public List<Product> all();
    public Product read( UUID id);
    public Product create(String name, Float price, String description);
    public Product create( String name, Float price, String description, List<String> categories);
    public Product update(UUID id, String name, Float price, String description, List<String> categories,
                          List<MultipartFile> files);
    public void removeFile(UUID pid, UUID fid);
    public void removePhoto(UUID pid, UUID fid);
    public void delete(@PathVariable(value = "id") UUID id);
    public List<Product> findAllByFiles(FileStorage f);
    public List<Product> searchByFile(String searchText);
    public List<Product> search(String searchText);
    public Product saveUploadedImage(List<MultipartFile> imageFile) throws Exception;
}
