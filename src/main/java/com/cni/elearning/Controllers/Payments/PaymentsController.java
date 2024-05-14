package com.cni.elearning.Controllers.Payments;

import com.cni.elearning.Models.Paiements.Payments;
import com.cni.elearning.Services.payments.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class PaymentsController {
    public final IPaymentService paymentService;

    @GetMapping("/")
    public List<Payments> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping("{id}")
    public Payments getPaymentsById(@PathVariable int id){
        return paymentService.getPaymentsById(id);
    }
    @PostMapping("/")
    public Payments addPayments(@RequestBody Payments payments){
        return paymentService.addPayments(payments);
    }
}
