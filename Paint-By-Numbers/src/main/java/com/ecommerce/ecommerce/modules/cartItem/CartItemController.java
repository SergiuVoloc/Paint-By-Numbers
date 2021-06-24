package com.ecommerce.ecommerce.modules.cartItem;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorage;
import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
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
    private PaypalService paypalService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private FileStorageService fileStorageService;


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


//////    Actions
    @PostMapping("/addProduct")
    public String addProduct(
            @RequestParam(value = "pid") UUID pid,
            @RequestParam(value = "qty") Integer qty,
            @RequestParam Boolean frame,
            @RequestParam String size,
            @RequestParam(value = "total") float total
    ){
        User user = userService.getCurrent();
        Product p = productServiceImpl.read(pid);
        cartItemService.create(new CartItem(qty, user, p, null, total, frame, size));
        return "redirect:/cart";
    }



//    @PostMapping("/addProduct")
//    public String addItemToCart(
//            @RequestParam(value = "pid") UUID productId,
//            @RequestParam String size,
//            @RequestParam(value = "qty") Integer quantity,
//            @RequestParam Boolean frame,
//            @RequestParam MultipartFile imageFile
//    ) throws IOException {
//
//        Product p = productServiceImpl.read(productId);
//        User user = userService.getCurrent();
//
////        cartItemService.create( quantity, user, productId, frame, imageFile, size);
//
//        return "redirect:/cart";
//    }



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


    // PayPal Payment endpoints for Checkout feature
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
        StringBuilder products = new StringBuilder();
        List<String> pbns = new ArrayList<>();
        ArrayList<CartItem> cartItems  = cartItemService.findAllByUser();
        for(CartItem cartItem: cartItems){
            if(cartItem.getProduct() != null){
                products
                        .append(cartItem.getProduct().getName())
                        .append("["+cartItem.getSize()+"+]")
                        .append("Frame:"+cartItem.getFrame())
                        .append(" | ")
                ;
            } else {
                products
                        .append(cartItem.getPbn().getName())
                        .append("["+cartItem.getSize()+"+]")
                        .append("Frame:"+cartItem.getFrame())
                        .append(" | ")
                ;
                for(FileStorage f : cartItem.getPbn().getFiles())
                    pbns.add(f.getPath());
            }
        }
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);

//          System.out.println(payment.toJSON());

//            // Regex expression to extract products name
//            Pattern pattern = Pattern.compile(" name='([A-Z].+?)',");
//            Matcher matcher = pattern.matcher(text);
//
//
//            while (matcher.find()){
//                purchasedItems.add(matcher.group(1));
//            }



            // save order details to file
            try (FileWriter file = new
                    FileWriter("src/main/resources/payments/" + paymentId + ".txt")) {

                file.write("\n Products: " + products +
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
                        "Paint By Numbers. Purcha   se confirmation! ",
                        "Your order " + paymentId +
                                " has been registered and soon will be sent to You! \n\n Your Products are: " + products + "." + "\n\n If You have any questions, reply to this mail." + "\n\n Regards, \n Paint By Numbers Team.");




                // Email with attachment details about new order for factory
//                emailSenderService.sendEmailWithAttachment(
//                        "voloc.sergiu.i7c@student.ucv.ro",
//                        "New Order! ",
//                        "<h1>Check attachment for details!</h1>",
//                        "src/main/resources/payments/" + paymentId + ".txt");
                pbns.add("src/main/resources/payments/" + paymentId + ".txt");
                emailSenderService.sendEmailWithAttachment(
                        "voloc.sergiu.i7c@student.ucv.ro",
                        "New Order! ",
                        "<h1>Check attachment for details!</h1>",
                        pbns
                        );




                // Empty users basket
                cartItemService.clear();
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
