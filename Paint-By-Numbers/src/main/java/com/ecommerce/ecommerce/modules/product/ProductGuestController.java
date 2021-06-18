package com.ecommerce.ecommerce.modules.product;

import com.ecommerce.ecommerce.modules.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("product")
public class ProductGuestController {

    @Autowired
    private ProductServiceImpl productServiceImpl;
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
        model.addAttribute("item", productServiceImpl.read(id));
        return "pages/product/read";
    }



    @GetMapping("/search")
    @ResponseBody
    public List<String> search(){
        List<String> list = new ArrayList<>();
        productServiceImpl.all().forEach((e)-> {
            list.add(e.getName());
        });
        return list;
    }

}
