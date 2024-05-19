package com.cni.elearning.Dtos.Payments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private double amount;
    private String note = "Subscription";
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String return_url = "https://localhost:4200/Learner/payment";
    private String cancel_url = "https://localhost:4200/Learner/payment";
    private String webhook_url = "https://localhost:4200/admin";
    private UUID order_id = UUID.randomUUID();
}
