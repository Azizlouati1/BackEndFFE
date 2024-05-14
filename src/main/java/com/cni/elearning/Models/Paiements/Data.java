package com.cni.elearning.Models.Paiements;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String note;
    private String phone;
    private String token;
    private String lastName;
    private String bindingId;
    private String orderId;
    private double amount;
    private String paymentUrl;
    private String firstName;
    private String email;

}
