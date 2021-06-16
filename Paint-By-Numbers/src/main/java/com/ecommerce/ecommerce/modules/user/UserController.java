package com.ecommerce.ecommerce.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.UUID;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping()
    public String all(Model model){
        model.addAttribute("list", userService.all());
        return "pages/user/index";
    }

    @GetMapping("/create")
    public  String addPage(Model model){
        return "pages/user/create";
    }

    @PostMapping()
    public String create(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String role,
            @RequestParam Date dateOfBirth,
            @RequestParam Boolean enabled
    ){
        User u = new User(username, password, fullName, email, phone, role, dateOfBirth, enabled);
        userService.create(u);
        return "redirect:" + request.getHeader("Referer");
    }


    @GetMapping("/{id}/edit")
    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", userService.read(id));
        return "pages/user/edit";
    }


    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable(value = "id") UUID id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String fullName,
            @RequestParam String phone,
            @RequestParam String role,
            @RequestParam Date dateOfBirth,
            @RequestParam Boolean enabled,
            @RequestParam String email
    ){
        User u = new User(username, password, fullName, email, phone, role, dateOfBirth, enabled);
        userService.update(id, u);
        return "redirect:/user";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        userService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }


//    @PostMapping("/{id}/add_address")
//    public String value4product(@PathVariable(value = "id") UUID id, @RequestParam String value, @RequestParam Attribute attribute){
////        userService.create(value, attribute, productService.read(id));
//        return "redirect:/product/" + id + "/edit";
//    }
}
