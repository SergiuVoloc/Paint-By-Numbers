package com.ecommerce.ecommerce.modules.payment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayPalOrder {

    private float price;
    private String currency;
    private String method;
    private String intent;
    private String description;
}
