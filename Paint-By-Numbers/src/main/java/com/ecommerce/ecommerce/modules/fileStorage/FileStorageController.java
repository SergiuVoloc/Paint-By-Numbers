package com.ecommerce.ecommerce.modules.fileStorage;

//import com.ecommerce.ecommerce.repo.ProductCategoryRepository;

import com.ecommerce.ecommerce.modules.product.Product;
import com.ecommerce.ecommerce.modules.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("admin/file")
public class FileStorageController {
    
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        fileStorageService.uploadFile(file);
        return "redirect:" + request.getHeader("Referer");
    }
    @PostMapping("/photo4product/{id}")
    public String photos4product(@PathVariable(value = "id") UUID id, @RequestParam("file") MultipartFile file) {
        FileStorage f = fileStorageService.uploadFile(file);
        Product p = productRepository.findById(id).orElseThrow();
        p.addPhoto(f);
        productRepository.save(p);
        return "redirect:" + request.getHeader("Referer");
    }
    @PostMapping("/file4product/{id}")
    public String files4product(@PathVariable(value = "id") UUID id, @RequestParam("file") MultipartFile file) {
        FileStorage f = fileStorageService.uploadFile(file, id.toString());
        Product p = productRepository.findById(id).orElseThrow();
        p.addFile(f);
        productRepository.save(p);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        fileStorageService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }

}
