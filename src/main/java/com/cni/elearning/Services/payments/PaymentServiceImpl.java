package com.cni.elearning.Services.payments;

import com.cni.elearning.Models.Paiements.Payments;
import com.cni.elearning.Repositories.Payments.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService{
    public final PaymentRepository paymentRepository;
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
}

