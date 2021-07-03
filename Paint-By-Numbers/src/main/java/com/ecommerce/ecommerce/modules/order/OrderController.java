package com.ecommerce.ecommerce.modules.order;

import com.ecommerce.ecommerce.modules.cartItem.CartItemService;
import com.ecommerce.ecommerce.modules.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private CartItemService cartItemService;


    @GetMapping()
    public String all(Model model){
        Iterable<Order> o = orderService.all();
        model.addAttribute("list", (ArrayList<Order>) o);
        return "pages/order/index";
    }
//    @GetMapping("/create")
//    public  String addPage(){
//        return "pages/order/create";
//    }
    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("order", orderService.read(id));
        return "pages/order/read";
    }
//    @GetMapping("/{id}/edit")
//    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
//        model.addAttribute("o", orderService.read(id));
//        return "pages/order/edit";
//    }



    @PostMapping("/create")
    public String create(
            @RequestParam float delivery_fee,
            @RequestParam String discount,
            @RequestParam UUID address
    ){
        orderService.create(delivery_fee, address, discount);

        return "redirect:/order";
//        return "redirect:/order/" + order.getId();
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
//        Order a = new Order(
//                full_name,
//                address1,
//                address2,
//                postcode,
//                phone
//        );
//        orderService.update(id, a);
        return "redirect:" + request.getHeader("Referer");
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        orderService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
