package sergiu.voloc.PaintByNumbers.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sergiu.voloc.PaintByNumbers.Service.CategoryService;
import sergiu.voloc.PaintByNumbers.Service.IProductService;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private IProductService IProductService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "") String productName){

        model
                .addAttribute("products",   IProductService.searchByName(productName))
                .addAttribute("categories", categoryService.all());
        return "/pages/homepage/index";
    }
}
