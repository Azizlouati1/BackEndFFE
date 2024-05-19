package com.cni.elearning.Services.payments;

import com.cni.elearning.Models.Paiements.Refund;

import java.util.List;

public interface IRefundService {
    Refund RequestRefund(Refund refund, int id);

    List<Refund> getAllRefunds();

    Refund notApproved(int id);
}
