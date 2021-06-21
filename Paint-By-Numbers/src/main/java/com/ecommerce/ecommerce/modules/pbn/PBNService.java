//package com.ecommerce.ecommerce.modules.pbn;
//
//
//import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
//import com.ecommerce.ecommerce.modules.utils.PBNUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.persistence.EntityManager;
//import java.io.IOException;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class PBNService {
//
//    private final EntityManager entityManager;
//
//    @Autowired
//    private PBNRepository pbnRepository;
//    @Autowired
//    private FileStorageService fileStorageService;
//    @Autowired
//    private PBNUtils pbnUtils;
//
//    public List<PBN> all(){
//        return (List<PBN>) pbnRepository.findAll();
//    }
//
//    public PBN read(UUID id){
//        return pbnRepository.findById(id).orElseThrow();
//    }
//
//    public PBN create(){
//        PBN n = new PBN();
//        pbnRepository.save(n);
//        return n;
//    }
//
//
//    public PBN create(String name, String size, MultipartFile file) throws IOException {
//        PBN n = new PBN();
//        n.setName(name);
//        n.setSize(size);
//        n.addFile(fileStorageService.uploadFile(file));
//        pbnRepository.save(n);
//
//
//
//        pbnUtils.process(n.getFiles().get(0).getPath());
//        return n;
//    }
//
//
//
////    public PBN update(UUID id, String name, Float price, String description, List<String> categories, List<MultipartFile> files){
////        PBN n = productRepository.findById(id).orElseThrow();
////        n.setName(name);
////        n.setDescription(description);
////        n.setPrice(price);
////        n.setCategories(new ArrayList<>());
////        n.setPhotos(new ArrayList<>());
////        categories.forEach( category -> {
////            Category c = categoryService.read(UUID.fromString(category));
////            n.addCategory(c);
////        });
////
////        files.forEach( file -> {
////            n.addPhoto(fileStorageService.uploadFile(file));
////        });
////
////        productRepository.save(n);
////        return n;
////    }
//
////    public void removeFile(UUID pid, UUID fid) {
////        PBN p = productRepository.findById(pid).orElseThrow();
////        FileStorage f = fileStorageService.read(fid);
////        p.removeFile(f);
////        fileStorageService.delete(f.getId());
////        productRepository.save(p);
////    }
//
//
//
//    public void delete(@PathVariable(value = "id") UUID id){
//        pbnRepository.deleteById(id);
//    }
//
//
////    public List<PBN> findAllByFiles(FileStorage f){
////        return pbnRepository.findAllByFiles(f);
////    }
////
////    public List<PBN> searchByFile(String searchText){
////        return fileStorageService.search(searchText);
////    }
//}
