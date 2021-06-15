package com.ecommerce.ecommerce.modules.register;


import com.ecommerce.ecommerce.modules.user.User;
import com.ecommerce.ecommerce.modules.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;


    @GetMapping("/")
    public  String registerPage(Model model){
        return "register";
    }


    @PostMapping()
    public String create(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String fullName,
            @RequestParam String phone,
            @RequestParam Date dateOfBirth

    ){
        User u = new User(username, password, fullName, phone, dateOfBirth);
        userService.create(u);
        return "redirect:/";
    }


}
