package com.ecommerce.ecommerce.modules.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String all(Model model){
        model.addAttribute("list", (ArrayList<Category>) categoryService.all());
        return "pages/category/index";
    }

    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", categoryService.read(id));
        return "pages/category/edit";
    }

    @GetMapping(value = "/create")
    public  String addPage(Model model){
        return "pages/category/create";
    }

    @GetMapping("/{id}/edit")
    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", categoryService.read(id));
        return "pages/category/edit";
    }

    @PostMapping()
    public String create(@RequestParam String name, HttpServletRequest request){
        categoryService.create(name);
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable(value = "id") UUID id, @RequestParam String name, HttpServletRequest request){
        Category c = categoryService.read(id);
        c.setName(name);
        categoryService.update(id, c);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id, HttpServletRequest request){
        categoryService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
