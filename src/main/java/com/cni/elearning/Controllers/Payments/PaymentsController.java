package com.cni.elearning.Controllers.Payments;

import com.cni.elearning.Dtos.Payments.PaymentRequest;
import com.cni.elearning.Dtos.Payments.PaymentResponse;
import com.cni.elearning.Models.Notifications.EmailDetails;
import com.cni.elearning.Models.Paiements.Payments;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Repositories.Users.StudentRepository;
import com.cni.elearning.Services.Notifications.IEmailService;
import com.cni.elearning.Services.payments.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@RestController
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class PaymentsController {
    public final IPaymentService paymentService;
    private final StudentRepository studentRepository;
    private final IEmailService emailService;

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
    public ResponseEntity<Payments> checkPaymentInfo(@PathVariable String token, @PathVariable int id) {
        String url = "https://sandbox.paymee.tn/api/v2/payments/{token}/check";
        String apiUrl = url.replace("{token}", token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token 1ebd9a7e4101918e9d1868fb9669b6a3c4657fc7");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Payments> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Payments.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Payments paymentInfo = response.getBody();
            assert paymentInfo != null;
            EmailDetails email = getEmailDetails(paymentInfo);

            if (!paymentInfo.getData().isPayment_status()) {
                return ResponseEntity.ok().body(null);
            }

            Student student = studentRepository.findById(id).orElse(null);
            assert student != null;
            paymentInfo.setStudent(student);
            Payments payments = paymentService.addPayments(paymentInfo);
            student.setIsSubscribed(true);
            int monthsToAdd = payments.getData().getReceived_amount() / 20;

            if (student.getDateEnd() == null || student.getDateEnd().isBefore(payments.getDateSubmit())) {
                student.setDateEnd(payments.getDateSubmit().plusMonths(monthsToAdd));
            } else {
                student.setDateEnd(student.getDateEnd().plusMonths(monthsToAdd));
            }

            studentRepository.save(student);
            emailService.sendSimpleMail(email);

            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }

    private static EmailDetails getEmailDetails(Payments paymentInfo) {
        assert paymentInfo != null;

        String subject = "Payment Confirmation - LearnCraft Subscription";

        // Encode images to base64

        // Improved HTML email content with base64 images
        String body = String.format("<html>"
                        + "<body style=\"font-family: Arial, sans-serif;\">"
                        + "<div style=\"text-align: center; margin-bottom: 20px;\">"
                        + "<img src=\"https://imgur.com/a/HyK0oJh.png\" alt=\"LearnCraft Logo\" style=\"width: 150px;\"/>"
                        + "</div>"
                        + "<p>Dear %s %s,</p>"
                        + "<p>We are delighted to confirm that your payment has been successfully processed.</p>"
                        + "<p>Below are the details of your transaction:</p>"
                        + "<ul>"
                        + "<li><strong>Payment Amount:</strong> $%d</li>"
                        + "<li><strong>Transaction Date:</strong> %s</li>"
                        + "</ul>"
                        + "<p>Thank you for subscribing to LearnCraft. We are thrilled to have you as part of our community. We hope you enjoy our services and find great value in your subscription.</p>"
                        + "<p>Should you have any questions or require further assistance, please do not hesitate to contact us at <a href=\"mailto:support@learncraft.com\">support@learncraft.com</a>.</p>"
                        + "<p>Best regards,</p>"
                        + "<div style=\"margin-top: 20px;\">"
                        + "<img src=\"https://imgur.com/a/HyK0oJh.png\" alt=\"Signature\" style=\"width: 100px;\"/>"
                        + "<p><strong>Your Name</strong><br/>"
                        + "Who you are and what you do at LearnCraft</p>"
                        + "</div>"
                        + "<hr/>"
                        + "<p style=\"font-size: 12px; color: #555;\">"
                        + "LearnCraft<br/>"
                        + "Innovate. Learn. Succeed.<br/>"
                        + "support@learncraft.com<br/>"
                        + "<a href=\"https://www.learncraft.com\">www.learncraft.com</a>"
                        + "</p>"
                        + "</body>"
                        + "</html>",

                paymentInfo.getData().getFirstname(),
                paymentInfo.getData().getLastname(),
                paymentInfo.getData().getReceived_amount(),
                paymentInfo.getDateSubmit().toString());

        return new EmailDetails(paymentInfo.getData().getEmail(), body, subject, null,"text/html");
    }
    @DeleteMapping("/{id}")
    public void deletePayments(@PathVariable int id){
        paymentService.deletePayments(id);
    }

}

