package com.cni.elearning.Models.Paiements;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean status;
    private String message;
    private int code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "data")
    private Data data;
    @Column(nullable = true)
    private boolean refund = false;

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private boolean paymentStatus;
    private String phone;
    private int cost;
    private double amount;
    private int receivedAmount;
    private String note;
    private String token;
    private String lastname;
    private String email;
    private int transactionId;
    private int buyerId;
}
