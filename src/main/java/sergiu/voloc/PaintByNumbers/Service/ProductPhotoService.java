package sergiu.voloc.PaintByNumbers.Service;

import sergiu.voloc.PaintByNumbers.Model.File_Storage;
import sergiu.voloc.PaintByNumbers.Model.Product_Photo;
import sergiu.voloc.PaintByNumbers.Repository.ProductPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPhotoService {

    @Autowired
    private ProductPhotoRepository productPhotoRepository;
    @Autowired
    private FileStorageService fileStorageService;

    public Product_Photo create(Product_Photo o){
        Product_Photo n = new Product_Photo();
        Iterable<File_Storage> photos = o.getFileStorage();
        photos.forEach( file -> {
            n.addPhoto(fileStorageService.read(file.getId()));
        });
        return productPhotoRepository.save(n);
    }


}
