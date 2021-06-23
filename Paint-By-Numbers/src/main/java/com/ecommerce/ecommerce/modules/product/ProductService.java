package com.ecommerce.ecommerce.modules.product;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
     List<Product> all();
     Product read( UUID id);
     Product create(String name, Float price, String description);
     Product create( String name, Float price, String description, List<String> categories);
     Product update(UUID id, String name, Float price, String description, List<String> categories,
                          List<MultipartFile> files);
     void removeFile(UUID pid, UUID fid);
     void removePhoto(UUID pid, UUID fid);
     void delete(@PathVariable(value = "id") UUID id);
     List<Product> findAllByFiles(FileStorage f);
     List<Product> searchByFile(String searchText);
     List<Product> search(String searchText);
     Product saveUploadedImage(List<MultipartFile> imageFile) throws Exception;

    Page<Product> findPaginated(int pageNo, int pageSize);
}
