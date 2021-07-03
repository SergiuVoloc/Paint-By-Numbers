package com.ecommerce.ecommerce;

import com.ecommerce.ecommerce.modules.category.CategoryService;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
import com.ecommerce.ecommerce.modules.product.Product;
import com.ecommerce.ecommerce.modules.product.ProductRepository;
import com.ecommerce.ecommerce.modules.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String index(
            @RequestParam(defaultValue = "") String searchText,
            @RequestParam(defaultValue = "") String searchInFile,
            Model model){

        if (searchText.length() > 0){
            model.addAttribute("products",   productServiceImpl.search(searchText));
        } else if(searchInFile.length() > 0 ) {
            model.addAttribute("products",   fileStorageService.search(searchInFile));
        } else {
            model.addAttribute("products",   productServiceImpl.all());
        }
        model.addAttribute("categories", categoryService.all());
//        return "index";
        return findPaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 6;

        Page<Product> page = productServiceImpl.findPaginated(pageNo, pageSize);

        List<Product> productList = page.getContent();

//        // creating UUID
//        UUID personalizedProduct = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
//
//
//        productList.removeIf(product -> product.getId(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")));

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("products", productList);

        model.addAttribute("categories", categoryService.all());

        return "index";
    }



    @GetMapping("/403")
    public String  error_403(){
        return "403";
    }


}
