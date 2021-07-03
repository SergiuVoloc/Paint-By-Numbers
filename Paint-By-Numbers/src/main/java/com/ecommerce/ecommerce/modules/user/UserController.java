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
    private UserServiceImpl userServiceImpl;
    @Autowired
    private HttpServletRequest request;


    // Display all users from database
    @GetMapping()
    public String all(Model model){
        model.addAttribute("list", userServiceImpl.all());
        return "pages/user/index";
    }

    // Create an user by Admin GET
    @GetMapping("/create")
    public  String addPage(Model model){
        return "pages/user/create";
    }

    // Create an user by Admin POST
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
        userServiceImpl.create(u);
        return "redirect:/user";
    }


    // Update an existing user GET
    @GetMapping("/{id}/edit")
    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", userServiceImpl.read(id));
        return "pages/user/edit";
    }

    // Update an existing user POST
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
        userServiceImpl.update(id, u);
        return "redirect:/user";
    }



    //  Account Details GET
    @GetMapping("/details")
    public String editAccountDetails(Model model){

        User user = userServiceImpl.getCurrent();

        model.addAttribute("user", user);

        return "pages/user/accountDetails";
    }

    //  Account Details POST
    @PostMapping("/details")
    public String updateAccountDetails(
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
        userServiceImpl.update(userServiceImpl.getCurrent().getId(), u);
        return "redirect:/";
    }



    // Delete an user
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        userServiceImpl.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }


}
