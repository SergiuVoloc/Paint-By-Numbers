package com.ecommerce.ecommerce.modules.authentication;


import com.ecommerce.ecommerce.modules.user.User;
import com.ecommerce.ecommerce.modules.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private HttpServletRequest request;


    @GetMapping("/register")
    public  String registerPage(Model model){
                User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("username", user);
        model.addAttribute("fullName", user);
        model.addAttribute("password", user);
        model.addAttribute("phone", user);
        model.addAttribute("dateOfBirth", user);
        model.addAttribute("email", user);
        return "register";
    }


//    @PostMapping()
//    public String create(
//            @RequestParam String username,
//            @RequestParam String password,
//            @RequestParam String fullName,
//            @RequestParam String phone,
//            @RequestParam Date dateOfBirth
//
//    ){
//        User u = new User(username, password, fullName, phone, dateOfBirth);
//        userService.create(u);
//        return "redirect:/";
//    }



    @RequestMapping(value="/register", method= RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(userServiceImpl.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage", "User with this Username already exists!");
        }
        // we will save the user if, no binding errors
        else {
            userServiceImpl.create(user);
            modelAndView.addObject("successMessage", "User is registered successfully!");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }


}
