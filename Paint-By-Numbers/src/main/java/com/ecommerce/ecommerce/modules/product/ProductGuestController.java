package com.ecommerce.ecommerce.modules.product;

import com.ecommerce.ecommerce.modules.attribute.AttributeService;
import com.ecommerce.ecommerce.modules.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("product")
public class ProductGuestController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HttpServletRequest request;

//    @GetMapping()
//    public String all(Model model){
//        Iterable<Product> o = productService.all();
//        model.addAttribute("list", (ArrayList<Product>) o);
//        return "index";
//    }

    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", productService.read(id));
        return "pages/product/read";
    }



    @GetMapping("/search")
    @ResponseBody
    public List<String> search(){
        List<String> list = new ArrayList<>();
        productService.all().forEach((e)-> {
            list.add(e.getName());
        });
        return list;
    }

}
