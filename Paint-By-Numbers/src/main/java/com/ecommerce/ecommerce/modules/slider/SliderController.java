package com.ecommerce.ecommerce.modules.slider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/admin/slider")
public class SliderController {

    @Autowired
    private SliderService sliderService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping()
    public String all(Model model){
        model.addAttribute("list", (ArrayList<Slider>) sliderService.all());
        return "pages/slider/index";
    }

    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", sliderService.read(id));
        return "pages/slider/edit";
    }

    @GetMapping(value = "/create")
    public  String addPage(Model model){
        return "pages/slider/create";
    }

    @GetMapping("/{id}/edit")
    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", sliderService.read(id));
        return "pages/slider/edit";
    }

    @PostMapping()
    public String create(
            @RequestParam String title,
            @RequestParam String subtitle,
            @RequestParam MultipartFile file
    ){
        sliderService.create(title, subtitle, file);
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable(value = "id") UUID id,
            @RequestParam String title,
            @RequestParam String subtitle,
            @RequestParam MultipartFile file

    ){
        Slider c = sliderService.read(id);
        sliderService.update(id, c);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        sliderService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
