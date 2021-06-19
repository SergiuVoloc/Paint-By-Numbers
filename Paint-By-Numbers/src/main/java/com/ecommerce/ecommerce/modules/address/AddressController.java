package com.ecommerce.ecommerce.modules.address;

import com.ecommerce.ecommerce.modules.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;


@Controller
public class AddressController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;



//    @GetMapping(value ="/user/address")
//    public String listAddresses(Model model){
//
//        List<Address> addressList = addressService.findAll();
//
//        // <addresses> is a variable from the index page
//        model.addAttribute("addresses", addressList);
//
//
//        return "pages/address/index";
//    }


    // returns specific user addresses
    @GetMapping(value ="/user/address")
    public String listAddresses(Model model){

        List<Address> userAddresses = userService.getCurrent().getAddresses();

        // <addresses> is a variable from the index page
        model.addAttribute("addresses", userAddresses);

        return "pages/address/index";
    }




    @GetMapping(value ="/user/address/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", userService.read(id));
        return "pages/address/read";
    }


    // Create Form
//    @GetMapping(value = "/user/address/create")
//    public  String addPage(Model model){
//
//        Address newAddress = new Address();
//
//
//        model.addAttribute("address", newAddress);
//
//        return "pages/address/create";
//    }

    @GetMapping(value = "/user/address/create")
    public  String addPage(Model model){

        Address newAddress = new Address();

        model.addAttribute("address", newAddress);

        return "pages/address/create";
    }





    //    Create/Update and save to db
    @PostMapping(value = "/user/address/save")
    public String saveAddress(@ModelAttribute("address") Address newAddress){

        // save the address
        addressService.save(newAddress);


        // use a redirect to prevent duplicate submission using the "Post/Redirect/Get" pattern
        return "redirect:/user/address";
    }


//    @PostMapping(value = "/user/address/create")
//    public String create(
//            @RequestParam String full_name,
//            @RequestParam String address1,
//            @RequestParam String address2,
//            @RequestParam String postcode,
//            @RequestParam String phone,
//            ){
//        addressService.create(full_name,address1,address2,postcode,phone);
//        return "pages/address/index";
//    }




    @GetMapping(value = "/edit/address/{id}")
    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", addressService.findById(id));
//        model.addAttribute("address", address);
        return "pages/address/create";
    }


    //    Update form, to prepopulated fields
    @GetMapping(value = "/edit/address")
    public String Update(@RequestParam("addressId") UUID theId, Model theModel){
        // addressId param is the same with one from pages/address/index.html on Edit btn

        // get specific address from service
        Address newAddress = addressService.findById(theId);

        // set address as a model attribute to pre-populate the form
        theModel.addAttribute("address", newAddress);

        // send over to the form
        return "pages/address/create";
    }



//    @PostMapping(value = "/edit/address/{id}")
//    public String update(
//            @PathVariable(value = "id") UUID id,
//            @RequestParam String fullName,
//            @RequestParam String address1,
//            @RequestParam String address2,
//            @RequestParam String postcode,
//            @RequestParam String phone
//    ){
//        Address a = new Address(fullName, address1, address2, postcode, phone);
//        addressService.update(id, a);
//        return "redirect:/user/address";
//    }
//

    @GetMapping(value = "/user/address/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        addressService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
