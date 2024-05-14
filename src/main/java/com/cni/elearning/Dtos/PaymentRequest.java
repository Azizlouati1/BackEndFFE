package com.cni.elearning.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private float amount;
    private String note;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String return_url;
    private String cancel_url;
    private String webhook_url;
    private String order_id;
}
