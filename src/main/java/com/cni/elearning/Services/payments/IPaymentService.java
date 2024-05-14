package com.cni.elearning.Services.payments;

import com.cni.elearning.Models.Paiements.Payments;

import java.util.List;

public interface IPaymentService {
    List<Payments> getAllPayments();

    Payments getPaymentsById(int id);

    Payments addPayments(Payments payments);
}
