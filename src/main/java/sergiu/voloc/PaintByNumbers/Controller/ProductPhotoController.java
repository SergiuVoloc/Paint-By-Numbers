package sergiu.voloc.PaintByNumbers.Controller;


import sergiu.voloc.PaintByNumbers.Service.CategoryService;
import sergiu.voloc.PaintByNumbers.Service.ProductPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sergiu.voloc.PaintByNumbers.Model.Product_Photo;

@Controller
@RequestMapping("productPhotos")
public class ProductPhotoController {
    @Autowired
    private ProductPhotoService productPhotoService;
    @Autowired
    private CategoryService categoryService;


//    @GetMapping()
//    public String product(Model model){
//        Iterable<Product> o = productService.all();
//        model.addAttribute("products", o);
//        return "pages/product/list";
//    }

//    @GetMapping("/{id}")
//    public String read(@PathVariable(value = "id") UUID id, Model model){
//        model
//                .addAttribute("categories", categoryService.all())
//                .addAttribute("item", productService.read(id));
//        return "pages/product/read";
//    }

    @PostMapping()
    public String create(@RequestBody Product_Photo o){
        productPhotoService.create(o);
        return "/pages/homepage/index";
    }

//    @PutMapping("/{id}")
//    public String update(@PathVariable(value = "id") UUID id, @RequestBody Product o){
//        productService.update(id, o);
//        return "/pages/homepage/index";
//    }
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable(value = "id") UUID id){
//        productService.delete(id);
//        return "/pages/homepage/index";
//    }
}
