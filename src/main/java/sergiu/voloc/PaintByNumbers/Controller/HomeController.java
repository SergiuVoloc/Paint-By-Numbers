package sergiu.voloc.PaintByNumbers.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sergiu.voloc.PaintByNumbers.Service.CategoryService;
import sergiu.voloc.PaintByNumbers.Service.ProductService;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model){

        model
                .addAttribute("products",   productService.all())
                .addAttribute("categories", categoryService.all());
        return "/pages/homepage/index";
    }
}
