package com.ecommerce.ecommerce.modules.pbn;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
import com.ecommerce.ecommerce.modules.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping()
public class PBNController {

    @Autowired
    private PBNService pbnService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ProductServiceImpl productServiceImpl;


    @GetMapping("/pbn")
    public String all(Model model){
        model.addAttribute("list", pbnService.all());
        return "pages/pbn/index";
    }




    @GetMapping(value ="pbn/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", pbnService.read(id));
        return "pages/pbn/read";
    }


    @GetMapping(value ="personalize/{id}")
    public String indexPage(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("product", productServiceImpl.read(id));
        return "pages/personalizedImages/index";
    }


    @PostMapping("personalize/create")
    public String addPersonalizedItemToCart(
            @RequestParam String name,
            @RequestParam String size,
            @RequestParam Integer quantity,
            @RequestParam Boolean frame,
            @RequestParam MultipartFile imageFile,
            @RequestParam float total
    ) throws IOException {

        pbnService.create(quantity, total, name, frame, size, imageFile);

        return "redirect:/cart";
    }


    @GetMapping("pbn/create")
    public  String addPage(Model model){
        return "pages/pbn/create";
    }



    @PostMapping("pbn/create")
    public String create(
            @RequestParam String name,
            @RequestParam MultipartFile file,
            @RequestParam Integer qty,
            @RequestParam float total,
            @RequestParam Boolean frame,
            @RequestParam String size
    ) throws IOException {
        pbnService.create(qty, total, name, frame, size, file);
        return "redirect:/pbn";
    }




    @GetMapping("pbn/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        pbnService.delete(id);
        return "redirect:/pbn";
    }


    @GetMapping("pbn/clean")
    public String clean(){
        for(PBN pbn : pbnService.all()){
            pbnService.delete(pbn.getId());
        }
        return "redirect:/pbn";
    }

}
