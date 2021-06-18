package com.ecommerce.ecommerce.modules.product;

import com.ecommerce.ecommerce.modules.attribute.AttributeService;
import com.ecommerce.ecommerce.modules.category.CategoryService;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("admin/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FileStorageService fileStorageService;


    @GetMapping()
    public String all(
            @RequestParam(defaultValue = "") String searchText,
            Model model
    ){
//        System.out.println("<--- Search --->");
        model.addAttribute("list", productServiceImpl.all());
        return "pages/product/index";
    }




    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", productServiceImpl.read(id));
        return "pages/product/read";
    }


    @GetMapping("/create")
    public  String addPage(Model model){
        model.addAttribute("categories", categoryService.all());
        return "pages/product/create";
    }



    @PostMapping()
    public String create(
            @RequestParam String name,
            @RequestParam Float price,
            @RequestParam String description,
            @RequestParam List<String> categories
    ){
        productServiceImpl.create(
                name,
                price,
                description,
                categories
        );
        return "redirect:/admin/product";
    }



    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", productServiceImpl.read(id));
        model.addAttribute("categories", categoryService.all());
        model.addAttribute("attributes", attributeService.all());
        return "pages/product/edit";
    }



    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable(value = "id") UUID id,
            @RequestParam String name,
            @RequestParam Float price,
            @RequestParam String description,
            @RequestParam List<String> categories,
            @RequestParam List<MultipartFile> files
    ){
        productServiceImpl.update(
                id,
                name,
                price,
                description,
                categories,
                files
        );
        return "pages/product/edit";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        productServiceImpl.delete(id);
        return "redirect:/admin/product";
    }


    @GetMapping("/{pid}/rFile/{fid}")
    public String removeFile (@PathVariable(value = "pid") UUID pid,@PathVariable(value = "fid") UUID fid){
        productServiceImpl.removeFile(pid, fid);
        return "redirect:" + request.getHeader("Referer");
    }


    @GetMapping("/{pid}/rPhoto/{fid}")
    public String removePhoto (@PathVariable(value = "pid") UUID pid,@PathVariable(value = "fid") UUID fid){
        productServiceImpl.removePhoto(pid, fid);
        return "redirect:" + request.getHeader("Referer");
    }

}
