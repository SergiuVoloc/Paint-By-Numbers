package com.ecommerce.ecommerce.modules.product;


import com.ecommerce.ecommerce.modules.category.Category;
import com.ecommerce.ecommerce.modules.category.CategoryService;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileStorageService fileStorageService;

    public List<Product> all(){
        return (List<Product>) productRepository.findAll();
    }

    public Product read( UUID id){
        return productRepository.findById(id).orElseThrow();
    }

    public Product create(String name, Float price, String description){
        Product n = new Product();
        n.setName(name);
        n.setPrice(price);
        n.setDescription(description);
        productRepository.save(n);
        return n;
    }


    public Product create( String name, Float price, String description, List<String> categories){
        Product n = new Product();
        n.setName(name);
        n.setDescription(description);
        n.setPrice(price);

        categories.forEach( category -> {
            Category c = categoryService.read(UUID.fromString(category));
            n.addCategory(c);
        });
        productRepository.save(n);
        return n;
    }



    public Product update(UUID id, String name, Float price, String description, List<String> categories, List<MultipartFile> files){
        Product n = productRepository.findById(id).orElseThrow();
        n.setName(name);
        n.setDescription(description);
        n.setPrice(price);
        n.setCategories(new ArrayList<>());
        n.setPhotos(new ArrayList<>());
        categories.forEach( category -> {
            Category c = categoryService.read(UUID.fromString(category));
            n.addCategory(c);
        });

        files.forEach( file -> {
            n.addPhoto(fileStorageService.uploadFile(file));
        });

        productRepository.save(n);
        return n;
    }

    public void removeFile(UUID pid, UUID fid) {
        Product p = productRepository.findById(pid).orElseThrow();
        FileStorage f = fileStorageService.read(fid);
        p.removeFile(f);
        fileStorageService.delete(f.getId());
        productRepository.save(p);
    }


    public void removePhoto(UUID pid, UUID fid) {
        Product p = productRepository.findById(pid).orElseThrow();
        FileStorage f = fileStorageService.read(fid);
        p.removePhoto(f);
        fileStorageService.delete(f.getId());
        productRepository.save(p);
    }

    public void delete(@PathVariable(value = "id") UUID id){
        productRepository.deleteById(id);
    }

//    public List<Product> search(String searchText, int pageNo, int resultsPerPage){
    public List<Product> findAllByFiles(FileStorage f){
        return productRepository.findAllByFiles(f);
    }

    public List<Product> searchByFile(String searchText){
        return fileStorageService.search(searchText);
    }

    public List<Product> search(String searchText){
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class)
                .get();

        Query foodQuery = qb.keyword()
                .onFields("name","description")
                .matching(searchText)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager
                .createFullTextQuery(foodQuery, Product.class);
//        fullTextQuery.setMaxResults(resultsPerPage);
//        fullTextQuery.setFirstResult((pageNo-1) * resultsPerPage);

        return (List<Product>) fullTextQuery.getResultList();
    }



    public Product saveUploadedImage(List<MultipartFile> imageFile) throws Exception {
        Product personalizedProduct = new Product();
        personalizedProduct.setPhotos(new ArrayList<>());
        imageFile.forEach( file -> {
            personalizedProduct.addPhoto(fileStorageService.uploadFile(file));
        });

        productRepository.save(personalizedProduct);
        return personalizedProduct;
    }




    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return this.productRepository.findAll(pageable);
    }

}
