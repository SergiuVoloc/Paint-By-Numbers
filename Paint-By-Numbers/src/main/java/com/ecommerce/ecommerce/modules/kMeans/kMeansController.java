package com.ecommerce.ecommerce.modules.kMeans;

import com.ecommerce.ecommerce.modules.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("/personalize_image")
public class kMeansController {

    @Autowired
    private ProductService productService;
    @Autowired
    private HttpServletRequest request;


    @GetMapping("/")
    public String mainPage(){
        return "pages/personalizedImages/index";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", productService.read(id));
        return "pages/personalizedImages/edit";
    }

    @GetMapping("/{pid}/rPhoto/{fid}")
    public String removePhoto (@PathVariable(value = "pid") UUID pid,@PathVariable(value = "fid") UUID fid){
        productService.removePhoto(pid, fid);
        return "redirect:" + request.getHeader("Referer");
    }
}
