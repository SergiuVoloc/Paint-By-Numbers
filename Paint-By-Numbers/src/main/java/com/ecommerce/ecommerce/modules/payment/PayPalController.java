//package com.ecommerce.ecommerce.modules.payment;
//
//
//import com.ecommerce.ecommerce.modules.order.Order;
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payment;
//import com.paypal.base.rest.PayPalRESTException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class PayPalController {
//
//    @Autowired
//    PaypalService paypalService;
//
//    public static final String SUCCESS_URL = "pay/success";
//    public static final String CANCEL_URL  = "pay/cancel";
//
//
//
//    @GetMapping(value = "/cart")
//    public String indexPage(){
//        return "pages/payment/index";
//    }
//
//    @PostMapping("/pay")
//    public String payment(@ModelAttribute("order") Order order) {
//        try {
//            Payment payment = paypalService.createPayment(
//                    order.getAmount(),
//                    order.getDelivery_fee(),
//                    order.getDescription(),
//                    "http://localhost:80/" + CANCEL_URL,
//                    "http://localhost:80/" + SUCCESS_URL);
//
//            for(Links link:payment.getLinks()) {
//                if(link.getRel().equals("approval_url")) {
//                    return "redirect:"+link.getHref();
//                }
//            }
//
//        } catch (PayPalRESTException e) {
//
//            e.printStackTrace();
//        }
//        return "redirect:/";
//    }
//
//
//    @GetMapping(value = CANCEL_URL)
//    public String cancelPay() {
//        return "pages/payment/cancel";
//    }
//
//    @GetMapping(value = SUCCESS_URL)
//    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
//        try {
//            Payment payment = paypalService.executePayment(paymentId, payerId);
//            System.out.println(payment.toJSON());
//            if (payment.getState().equals("approved")) {
//                return "pages/payment/success";
//            }
//        } catch (PayPalRESTException e) {
//            System.out.println(e.getMessage());
//        }
//        return "redirect:/";
//    }
//
//
//}
