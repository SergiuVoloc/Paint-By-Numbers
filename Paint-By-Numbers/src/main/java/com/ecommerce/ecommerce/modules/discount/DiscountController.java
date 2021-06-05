package com.ecommerce.ecommerce.modules.discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/admin/discount")
public class DiscountController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private DiscountService discountService;


    @GetMapping()
    public String all(Model model){
        model.addAttribute("list", discountService.all());
        return "pages/discount/index";
    }
    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", discountService.read(id));
        return "pages/discount/edit";
    }
    @GetMapping("/{id}/create")
    public  String addPage(@PathVariable(value = "id") UUID id,Model model){
        return "pages/address/create";
    }
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("o", discountService.read(id));
        return "pages/discount/edit";
    }

    @PostMapping("/{id}/create")
    public String create(
            @PathVariable(value = "id") UUID id,
            @RequestParam String name
    ){
//        Discount a = new Discount(name);
//        discountService.create(a);
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable(value = "id") UUID id,
            @RequestParam String name
    ){
//        Discount a = new Discount(name);
//        discountService.update(id, a);
        return "redirect:" + request.getHeader("Referer");
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        discountService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
