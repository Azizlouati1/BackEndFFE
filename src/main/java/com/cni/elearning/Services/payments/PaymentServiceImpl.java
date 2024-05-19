package com.cni.elearning.Services.payments;

import com.cni.elearning.Models.Paiements.Payments;
import com.cni.elearning.Models.Paiements.Refund;
import com.cni.elearning.Repositories.Payments.PaymentRepository;
import com.cni.elearning.Repositories.Payments.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService{
    public final PaymentRepository paymentRepository;
    public final RefundRepository refundRepository;
    @Override
    public List<Payments> getAllPayments(){
        return paymentRepository.findAll();
    }
    @Override
    public Payments getPaymentsById(int id){
        return paymentRepository.findById(id).orElse(null);
    }
    @Override
    public Payments addPayments(Payments payments){
        return paymentRepository.save(payments);
    }
    @Override
    public Payments getPaymentsByDataTransaction_id(int id){
        return paymentRepository.getPaymentsByDataTransaction_id(id);
    }
    @Override
    public Payments updatePayments(Payments payments , int id){
        return paymentRepository.save(payments);
    }
    @Override
    public Boolean checkPayments(int id){
        Payments payments =paymentRepository.findById(id).orElse(null);
        assert payments != null;
        return payments.getData().isPayment_status();
    }
    @Override
    public void deletePayments(int id){
        paymentRepository.deleteById(id);
    }

}

