package sergiu.voloc.PaintByNumbers.Controller;

import sergiu.voloc.PaintByNumbers.Model.Product;
import sergiu.voloc.PaintByNumbers.Service.CategoryService;
import sergiu.voloc.PaintByNumbers.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.UUID;


@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping()
    public String all(Model model){
        Iterable<Product> o = productService.all();
        model.addAttribute("list", (ArrayList<Product>) o);
        return "pages/product/index";
    }



    @GetMapping("/create")
    public  String addPage(Model model){
        return "pages/product/create";
    }



    @GetMapping(value = "/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", productService.read(id));
        return "pages/product/info";
    }



    @PostMapping()
    public String create(@RequestParam String name, @RequestParam Float price, @RequestParam String description){
        productService.create(name, price, description);
        return "redirect:/product";
    }




    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") UUID id, Model model){
                model.addAttribute("item", productService.read(id));
                model.addAttribute("categories", categoryService.all());
                return "pages/product/edit";
    }


    @PostMapping("/{id}/edit")
    public String update(@PathVariable(value = "id") UUID id, @RequestParam String name, @RequestParam Float price, @RequestParam String description){
                productService.update(id, name, price, description);
                return "redirect:/product/" + id;
            }



    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        productService.delete(id);
        return "redirect:/product";
    }





    //    @GetMapping()
//    public String product(Model model){
        //        Iterable<Product> o = productService.all();
        //        model.addAttribute("products", o);
        //        return "pages/product/index";
        //    }
        //
        //    @GetMapping("/{id}")
        //    public String read(@PathVariable(value = "id") UUID id, Model model){
        //        model
        //            .addAttribute("categories", categoryService.all())
        //            .addAttribute("item", productService.read(id));
        //        return "pages/product/read";
        //    }
        //
        //    @PostMapping()
        //    public String create(@RequestBody Product o, @RequestParam("file") MultipartFile file){
        //        productService.create(o, file);
        //        return "index";
        //    }
        //
        //    @PutMapping("/{id}")
        //    public String update(@PathVariable(value = "id") UUID id, @RequestBody Product o){
        //        productService.update(id, o);
        //        return "index";
        //    }
        //    @DeleteMapping("/{id}")
        //    public String delete(@PathVariable(value = "id") UUID id){
        //        productService.delete(id);
        //        return "index";
        //    }
        
}
    
    

