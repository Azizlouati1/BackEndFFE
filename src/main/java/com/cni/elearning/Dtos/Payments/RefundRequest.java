package com.cni.elearning.Dtos.Payments;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefundRequest {
    private int transaction_id;
    private double amount;
}