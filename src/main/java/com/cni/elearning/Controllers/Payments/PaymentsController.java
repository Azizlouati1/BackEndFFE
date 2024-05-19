package com.cni.elearning.Controllers.Payments;

import com.cni.elearning.Dtos.Payments.PaymentRequest;
import com.cni.elearning.Dtos.Payments.PaymentResponse;
import com.cni.elearning.Dtos.Payments.RefundRequest;
import com.cni.elearning.Models.Paiements.Payments;
import com.cni.elearning.Models.Paiements.Refund;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Repositories.Payments.RefundRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import com.cni.elearning.Services.payments.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import java.util.List;


@RestController
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class PaymentsController {
    public final IPaymentService paymentService;
    private final StudentRepository studentRepository;
    private final RefundRepository refundRepository;

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
    @PostMapping("/create/{id}")
    public ResponseEntity<PaymentResponse> createPayment(@PathVariable int id , @RequestBody PaymentRequest amount) {
        String url = "https://sandbox.paymee.tn/api/v2/payments/create";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token 1ebd9a7e4101918e9d1868fb9669b6a3c4657fc7");
        headers.setContentType(MediaType.APPLICATION_JSON);
        PaymentRequest paymentRequest = new PaymentRequest();
        Student student = studentRepository.findById(id).orElse(null);
        assert student != null;
        paymentRequest.setAmount(amount.getAmount());
        paymentRequest.setEmail(student.getEmail());
        paymentRequest.setLast_name(student.getLastname());
        paymentRequest.setFirst_name(student.getFirstname());
        paymentRequest.setPhone(student.getPhoneNumber());

        HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PaymentResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, PaymentResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            PaymentResponse responseBody = response.getBody();
            assert responseBody != null;
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }

    @PostMapping("/{token}/check/{id}")
    public ResponseEntity<Payments> checkPaymentInfo(@PathVariable String token,@PathVariable int id) {
        String url = "https://sandbox.paymee.tn/api/v2/payments/{token}/check";
        String apiUrl = url.replace("{token}", token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token 4e9385e30b65c688d6d28de68156c4870e350887");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Payments> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Payments.class);

        if (response.getStatusCode().is2xxSuccessful()) {

            Payments paymentInfo = response.getBody();
            assert paymentInfo != null;
            if(!paymentInfo.getData().isPayment_status()){
               return  ResponseEntity.ok().body(null);
            }
            Student student = studentRepository.findById(id).orElse(null);
            assert student != null;
            paymentInfo.setStudent(student);
            Payments payments = paymentService.addPayments(paymentInfo);
            student.setIsSubscribed(true);
            int monthsToAdd = (int) (payments.getData().getReceived_amount() / 20);
            if(student.getDateEnd() == null || student.getDateEnd().isBefore(payments.getDateSubmit())) {
                student.setDateEnd(payments.getDateSubmit().plusMonths(monthsToAdd));
            }
            else{
                student.setDateEnd(student.getDateEnd().plusMonths(monthsToAdd));
            }
            studentRepository.save(student);
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public void deletePayments(@PathVariable int id){
        paymentService.deletePayments(id);
    }

}
