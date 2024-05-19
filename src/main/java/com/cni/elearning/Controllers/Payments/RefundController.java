package com.cni.elearning.Controllers.Payments;

import com.cni.elearning.Dtos.Payments.RefundRequest;
import com.cni.elearning.Models.Paiements.Payments;
import com.cni.elearning.Models.Paiements.Refund;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Repositories.Payments.RefundRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import com.cni.elearning.Services.payments.IPaymentService;
import com.cni.elearning.Services.payments.IRefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refund")
public class RefundController {
    private final IPaymentService paymentService;
    private final IRefundService refundService;
    private final RefundRepository refundRepository;
    private final StudentRepository studentRepository;
    @PostMapping("/{id}")
    public Refund refundRequest(@PathVariable int id, @RequestBody Refund refund){
        return refundService.RequestRefund(refund,id);
    }
    @PostMapping("/approved/{id}")
    public ResponseEntity<Refund> refundPayment(@PathVariable int id) {
        String url = "https://sandbox.paymee.tn/api/v2/payments/refund";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token 1ebd9a7e4101918e9d1868fb9669b6a3c4657fc7");
        headers.setContentType(MediaType.APPLICATION_JSON);
        Refund refund = refundRepository.findById(id).orElse(null);
        assert refund != null;
        Student student = studentRepository.findById(refund.getStudent().getId()).orElse(null);
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setAmount(refund.getMonths()*20);
        refundRequest.setTransaction_id(refund.getTransactionId());
        HttpEntity<RefundRequest> entity = new HttpEntity<>(refundRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        assert student != null;
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            Payments payments = paymentService.getPaymentsByDataTransaction_id(refundRequest.getTransaction_id());
            payments.setRefund(true);
            int monthsToRemove = (int) (refundRequest.getAmount() / 20);
            paymentService.updatePayments(payments,payments.getId());
            student.setDateEnd(student.getDateEnd().minusMonths(monthsToRemove));
            LocalDateTime localDateTime = LocalDateTime.now();
            if(student.getDateEnd().isBefore(localDateTime)){
                student.setIsSubscribed(false);
            }
            studentRepository.save(student);
            refund.setApproved(true);
            refund.setTimeApproved(LocalDateTime.now());
            refundRepository.save(refund);
            return ResponseEntity.ok(refund);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }
    @GetMapping("/")
    public List<Refund> getAllRefunds(){
        return refundService.getAllRefunds();
    }
    @PutMapping("/notApproved/{id}")
    public Refund notApproved(@PathVariable int id){
        return refundService.notApproved(id);
    }
}
