package com.ecommerce.ecommerce;

import com.ecommerce.ecommerce.modules.category.CategoryService;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
import com.ecommerce.ecommerce.modules.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping()
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
        return "index";
    }


    @GetMapping("/403")
    public String  error_403(){
        return "403";
    }


}
