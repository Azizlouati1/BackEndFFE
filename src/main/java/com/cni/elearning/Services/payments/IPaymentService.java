package com.cni.elearning.Services.payments;

import com.cni.elearning.Models.Paiements.Payments;
import com.cni.elearning.Models.Paiements.Refund;

import java.util.List;

public interface IPaymentService {
    List<Payments> getAllPayments();

    Payments getPaymentsById(int id);

    Payments addPayments(Payments payments);

    Payments getPaymentsByDataTransaction_id(int id);

    Payments updatePayments(Payments payments, int id);


    Boolean checkPayments(int id);

    void deletePayments(int id);

}
