package com.ecommerce.ecommerce.modules.cartItem;

import com.ecommerce.ecommerce.modules.order.Order;
import com.ecommerce.ecommerce.modules.payment.PaypalService;
import com.ecommerce.ecommerce.modules.product.Product;
import com.ecommerce.ecommerce.modules.product.ProductServiceImpl;
import com.ecommerce.ecommerce.modules.user.User;
import com.ecommerce.ecommerce.modules.user.UserService;
import com.ecommerce.ecommerce.modules.utils.EmailSenderService;
import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/cart")
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

    @Autowired
    EmailSenderService emailSenderService;


    public static final String SUCCESS_URL = "/pay/success";
    public static final String CANCEL_URL  = "/pay/cancel";



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
    public String payment(@ModelAttribute("order") Order order, Product product) {


        try {
            Payment payment = paypalService.createPayment(
                    cartItemService.total(),
                    order.getDelivery_fee(),
                    order.getDescription(),
                    order.getCurrency(),
                    order.getMethod(),
                    order.getIntent(),
                    "http://localhost/cart" + CANCEL_URL,
                    "http://localhost/cart" + SUCCESS_URL);

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

//          System.out.println(payment.toJSON());

            // Regex expression to extract products name
            Pattern pattern = Pattern.compile(" name='([A-Z].+?)',");
            Matcher matcher = pattern.matcher(cartItemService.findAllByUser().toString());

            List<String> purchasedItems = new ArrayList<String>();

            while (matcher.find()){

                purchasedItems.add(matcher.group(1));
            }


            // save order details to file
            try (FileWriter file = new
                    FileWriter("src/main/resources/payments/" + paymentId + ".txt")) {

                file.write("\n Products: " + purchasedItems +
                        "\n\n Order Details: \n" + payment.toJSON());

                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (payment.getState().equals("approved")) {

                String receiverEmail = payment.getPayer().getPayerInfo().getEmail();

                // Email for Customer with purchase confirmation
                emailSenderService.sendPurchaseConfirmationEmail(
                        receiverEmail,
                        "Paint By Numbers. Purchase confirmation! ",
                        "Your order " + paymentId +
                                " has been registered and soon will be sent to You! \n\n Your Products are: " + purchasedItems + "." + "\n\n If You have any questions, reply to this mail." + "\n\n Regards, \n Paint By Numbers Team.");


                // Email with attachment details about order for factory
                emailSenderService.sendEmailWithAttachment(
                        "voloc.sergiu.i7c@student.ucv.ro",
                        "New Order! ",
                        "<h1>Check attachment for details!</h1>",
                        "src/main/resources/payments/" + paymentId + ".txt");

                return "pages/payment/success";
            }
        } catch (PayPalRESTException  e) {
            System.out.println(e.getMessage());

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/cart" + CANCEL_URL;
    }

}
