package com.cni.elearning.Services.payments;

import com.cni.elearning.Models.Paiements.Payments;
import com.cni.elearning.Models.Paiements.Refund;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Repositories.Payments.PaymentRepository;
import com.cni.elearning.Repositories.Payments.RefundRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements IRefundService{
    private final PaymentRepository paymentRepository;
    private final RefundRepository refundRepository;
    private final StudentRepository studentRepository;
    @Override
    public Refund RequestRefund(Refund refund, int id) {
        Payments payments = paymentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid payment ID: " + id));

        if (refund.getStudent() == null) {
            throw new IllegalArgumentException("Refund must have a student associated with it");
        }
        int studentId = refund.getStudent().getId();
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + studentId));

        boolean checkMonths = payments.getData().getReceived_amount() / 20 >= refund.getMonths();
        if (payments.getData().getTransaction_id() == refund.getTransactionId() &&
                !refundRepository.existsById(refund.getTransactionId()) &&
                student.equals(payments.getStudent())&& checkMonths ){
            return refundRepository.save(refund);
        }

        throw new RuntimeException("Refund request already sent or other validation failed");
    }
    @Override
    public List<Refund> getAllRefunds(){
        return refundRepository.findAll();
    }
    @Override
    public Refund notApproved(int id){
        Refund refund = refundRepository.findById(id).orElse(null);
        assert refund != null;
        refund.setApproved(false);
        refund.setTimeApproved(LocalDateTime.now());
        return refundRepository.save(refund);
    }
}
