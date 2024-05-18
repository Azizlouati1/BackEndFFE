package com.cni.elearning.Dtos.Payments;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private boolean status;
    private String message;
    private int code;
    private PaymentData data;
}

