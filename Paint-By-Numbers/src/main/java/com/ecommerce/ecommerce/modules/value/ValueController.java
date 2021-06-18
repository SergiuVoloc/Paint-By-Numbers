package com.ecommerce.ecommerce.modules.value;

import com.ecommerce.ecommerce.modules.attribute.Attribute;
import com.ecommerce.ecommerce.modules.attribute.AttributeService;
import com.ecommerce.ecommerce.modules.product.Product;
import com.ecommerce.ecommerce.modules.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("admin/value")
public class ValueController {

    @Autowired
    private ValueService valueService;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/create")
    public  String addPage(Model model){
        model.addAttribute("products", (ArrayList<Product>) productServiceImpl.all());
        model.addAttribute("attributes", (ArrayList<Attribute>) attributeService.all());
        return "pages/value/create";
    }

    @GetMapping()
    public String all(Model model){
        model.addAttribute("list", (ArrayList<Value>) valueService.all());
        model.addAttribute("products", (ArrayList<Product>) productServiceImpl.all());
        model.addAttribute("attributes", (ArrayList<Attribute>) attributeService.all());
        return "pages/value/index";
    }

    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", valueService.read(id));
        return "pages/value/edit";
    }

    @GetMapping("/{id}/edit")
    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", valueService.read(id));
        return "pages/value/edit";
    }

    @PostMapping()
    public String create(@RequestParam String value, @RequestParam Attribute attribute, @RequestParam Product product){
        valueService.create(value, attribute, product);
        return "redirect:" + request.getHeader("Referer");
    }



    @PostMapping("/{id}/edit")
    public String update(@PathVariable(value = "id") UUID id, @RequestParam String name){
        Value c = valueService.read(id);
        valueService.update(id, c);
        return "redirect:" + request.getHeader("Referer");
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        valueService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }


    @PostMapping("/value4product/{id}")
    public String value4product(
            @PathVariable(value = "id") UUID id,
            @RequestParam String value,
            @RequestParam Attribute attribute
    ){
        valueService.create(value, attribute, productServiceImpl.read(id));
        return "redirect:" + request.getHeader("Referer");
    }
}
