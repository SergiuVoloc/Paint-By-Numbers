package sergiu.voloc.PaintByNumbers.Service;

import sergiu.voloc.PaintByNumbers.Model.Category;
import sergiu.voloc.PaintByNumbers.Model.Product;
import sergiu.voloc.PaintByNumbers.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;


    @Override
    public Iterable<Product> all(){
        return productRepository.findAll();
    }


    @Override
    public Product read(UUID id){
        return productRepository.findById(id).orElseThrow();
    }


    @Override
    public Product create(String name, Float price, String description){
        Product n = new Product();
        n.setName(name);
        n.setPrice(price);
        n.setDescription(description);
        productRepository.save(n);
        return n;
    }

//    public Product create( Product o, MultipartFile file){
//        Product n = new Product();
//        n.setName(o.getName());
//        n.setDescription(o.getDescription());
//        n.setPrice(o.getPrice());
//
//        Iterable<Category> categories = o.getCategories();
//        categories.forEach( category -> {
//            Category c = categoryService.read(category.getId());
//            n.addCategory(c);
//        });
////        productPhotoService.create(file);
//        productRepository.save(n);
//        return n;
//    }



    @Override
    public Product update(UUID id, String name, Float price, String description){
        Product n = productRepository.findById(id).orElseThrow();
        n.setName(name);
        n.setPrice(price);
        n.setDescription(description);

//        categories.forEach( category -> {
//            Category c = categoryService.read(UUID.fromString(category));
//            n.addCategory(c);
//        });

        productRepository.save(n);
        return n;
    }



    @Override
    public void delete(@PathVariable(value = "id") UUID id){
        productRepository.deleteById(id);
    }


    // this method implements Search field by product name
    @Override
    public Iterable<Product> searchByName(String productName) {
        return productRepository.findByNameLike("%" + productName +"%");
    }

    // Predictive Search
    @Override
    public List<String> search(String keyword) {
        return productRepository.search(keyword);
    }

}
