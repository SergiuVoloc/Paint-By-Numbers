package com.ecommerce.ecommerce.modules.orderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/admin/order-status")
public class OrderStatusController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OrderStatusService orderStatusService;


    @GetMapping()
    public String all(Model model){
        Iterable<OrderStatus> o = orderStatusService.all();
        model.addAttribute("list", (ArrayList<OrderStatus>) o);
        return "pages/orderStatus/index";
    }
    @GetMapping("/create")
    public  String addPage(){
        return "pages/orderStatus/create";
    }
    @GetMapping(value ="/{id}")
    public String read(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", orderStatusService.read(id));
        return "pages/orderStatus/edit";
    }
    @GetMapping("/{id}/edit")
    public String editPAge(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("item", orderStatusService.read(id));
        return "pages/orderStatus/edit";
    }



    @PostMapping("/create")
    public String create(
            @RequestParam String name
    ){
        OrderStatus a = new OrderStatus(name);
        orderStatusService.create(a);
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable(value = "id") UUID id,
            @RequestParam String name
    ){
        OrderStatus a = new OrderStatus(name);
        orderStatusService.update(id, a);
        return "redirect:" + request.getHeader("Referer");
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        orderStatusService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
