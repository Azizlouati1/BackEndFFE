package com.cni.elearning.Dtos.Payments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentData {
    private String note;
    private String phone;
    private String token;
    private String last_name;
    private String binding_id;
    private String order_id;
    private double amount;
    private String payment_url;
    private String first_name;
    private String email;
}
