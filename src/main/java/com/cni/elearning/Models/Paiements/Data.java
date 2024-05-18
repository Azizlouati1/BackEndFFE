package com.cni.elearning.Models.Paiements;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private boolean payment_status;
    private String phone;
    private int cost;
    private double amount;
    private int received_amount;
    private String note;
    private String token;
    private String lastname;
    private String email;
    @Column(unique = true)
    private int transaction_id;
    private int buyer_id;
}
