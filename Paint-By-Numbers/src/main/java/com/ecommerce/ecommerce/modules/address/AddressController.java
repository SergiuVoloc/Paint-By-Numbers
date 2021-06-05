package com.ecommerce.ecommerce.modules.address;

import com.ecommerce.ecommerce.modules.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/admin/address")
public class AddressController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;


    @GetMapping()
    public String all(Model model){
        Iterable<Address> o = addressService.all();
        model.addAttribute("list", (ArrayList<Address>) o);
        return "pages/address/index";
    }
    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", addressService.read(id));
        return "pages/address/edit";
    }
    @GetMapping("/{id}/create")
    public  String addPage(@PathVariable(value = "id") UUID id,Model model){
        return "pages/address/create";
    }
    @GetMapping("/{id}/edit")
    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("o", addressService.read(id));
        return "pages/address/edit";
    }

    @PostMapping("/{id}/create")
    public String create(
            @PathVariable(value = "id") UUID id,
            @RequestParam String full_name,
            @RequestParam String address1,
            @RequestParam String address2,
            @RequestParam String postcode,
            @RequestParam String phone
    ){
        Address a = new Address(
                full_name,
                address1,
                address2,
                postcode,
                phone
        );
        a.setUser(userService.read(id));
        addressService.create(a);
        return "redirect:/user" + a.getUser().getId();
//        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable(value = "id") UUID id,
            @RequestParam String full_name,
            @RequestParam String address1,
            @RequestParam String address2,
            @RequestParam String postcode,
            @RequestParam String phone
    ){
        Address a = new Address(
                full_name,
                address1,
                address2,
                postcode,
                phone
        );
        addressService.update(id, a);
        return "redirect:" + request.getHeader("Referer");
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        addressService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }
}