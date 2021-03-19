package sergiu.voloc.PaintByNumbers.Controller;

import sergiu.voloc.PaintByNumbers.Model.Product;
import sergiu.voloc.PaintByNumbers.Service.CategoryService;
import sergiu.voloc.PaintByNumbers.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String product(Model model){
        Iterable<Product> o = productService.all();
        model.addAttribute("products", o);
        return "pages/product/list";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model
            .addAttribute("categories", categoryService.all())
            .addAttribute("item", productService.read(id));
        return "pages/product/info";
    }

    @PostMapping()
    public String create(@RequestBody Product o, @RequestParam("file") MultipartFile file){
        productService.create(o, file);
        return "/pages/homepage/index";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable(value = "id") UUID id, @RequestBody Product o){
        productService.update(id, o);
        return "/pages/homepage/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") UUID id){
        productService.delete(id);
        return "/pages/homepage/index";
    }

}
