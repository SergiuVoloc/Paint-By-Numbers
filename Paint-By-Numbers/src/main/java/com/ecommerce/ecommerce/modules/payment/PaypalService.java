package com.ecommerce.ecommerce.modules.payment;


import com.ecommerce.ecommerce.modules.cartItem.CartItemService;
import com.ecommerce.ecommerce.modules.product.ProductServiceImpl;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService {

    @Autowired
    private APIContext apiContext;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductServiceImpl productService;


    public Payment createPayment(
            float total,
            float deliveryFee,
            String description,
            String currency,
            String method,
            String intent,
            String cancelUrl,
            String successUrl) throws PayPalRESTException{

        Amount amount = new Amount();
        amount.setCurrency(currency);
        deliveryFee = new BigDecimal(deliveryFee).setScale(2,RoundingMode.HALF_UP).floatValue();
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).floatValue();

        float finalPrice = total + deliveryFee;

        amount.setTotal(String.format("%.2f", finalPrice));


        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);


        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

}
