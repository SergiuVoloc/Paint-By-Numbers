package com.ecommerce.ecommerce.modules.cartItem;

import com.ecommerce.ecommerce.modules.product.Product;
import com.ecommerce.ecommerce.modules.product.ProductServiceImpl;
import com.ecommerce.ecommerce.modules.user.User;
import com.ecommerce.ecommerce.modules.user.UserService;
import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("cart")
public class CartItemController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ObjectUtils objectUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductServiceImpl productServiceImpl;


//    Pages
    @GetMapping()
    public String all(Model model){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getCurrent();
        model.addAttribute("list", cartItemService.findAllByUser());
        model.addAttribute("subtotal", cartItemService.total());
        model.addAttribute("user", user);
        return "pages/cartItem/index";
    }


//    Actions
    @PostMapping("/addProduct")
    public String addProduct(
            @RequestParam(value = "pid") UUID pid,
            @RequestParam(value = "qty") Integer qty
    ){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getCurrent();
        Product p = productServiceImpl.read(pid);
        cartItemService.create(new CartItem(qty, user, p));
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(value = "id") UUID id){
        cartItemService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/{id}/addQty")
    public String addQty(@PathVariable(value = "id") UUID id){
        cartItemService.addQty(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/{id}/decreaseQty")
    public String decreaseQty(@PathVariable(value = "id") UUID id){
        cartItemService.decreaseQty(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
