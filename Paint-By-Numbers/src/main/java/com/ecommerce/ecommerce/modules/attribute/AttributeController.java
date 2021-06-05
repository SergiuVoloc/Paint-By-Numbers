package com.ecommerce.ecommerce.modules.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("admin/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/create")
    public  String addPage(Model model){
        return "pages/attribute/create";
    }

    @GetMapping()
    public String all(Model model){
        Iterable<Attribute> o = attributeService.all();
        model.addAttribute("list", (ArrayList<Attribute>) o);
        return "pages/attribute/index";
    }
    @GetMapping("/{id}/edit")
    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", attributeService.read(id));
        return "pages/attribute/edit";
    }

    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", attributeService.read(id));
        return "pages/attribute/edit";
    }
    @PostMapping()
    public String create(@RequestParam String name){
        attributeService.create(name);
        return "redirect:" + request.getHeader("Referer");
    }



    @PostMapping("/{id}/edit")
    public String update(@PathVariable(value = "id") UUID id, @RequestParam String name){
        Attribute c = attributeService.read(id);
        c.setName(name);
        attributeService.update(id, c);
        return "redirect:" + request.getHeader("Referer");
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        attributeService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
