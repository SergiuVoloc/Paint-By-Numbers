package com.ecommerce.ecommerce.modules.cartItem;

import com.ecommerce.ecommerce.modules.order.Order;
import com.ecommerce.ecommerce.modules.payment.PaypalService;
import com.ecommerce.ecommerce.modules.product.Product;
import com.ecommerce.ecommerce.modules.product.ProductServiceImpl;
import com.ecommerce.ecommerce.modules.user.User;
import com.ecommerce.ecommerce.modules.user.UserService;
import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
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

    @Autowired
    PaypalService paypalService;

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL  = "pay/cancel";



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


    // PayPal Payment endpoints
    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
        try {
            Payment payment = paypalService.createPayment(
                    cartItemService.total(),
                    order.getDelivery_fee(),
                    order.getDescription(),
                    order.getCurrency(),
                    order.getMethod(),
                    order.getIntent(),
                    "http://localhost:80/" + CANCEL_URL,
                    "http://localhost:80/" + SUCCESS_URL);

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }


    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "pages/payment/cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "pages/payment/success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }


}
