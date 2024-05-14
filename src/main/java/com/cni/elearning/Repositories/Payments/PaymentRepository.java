package com.cni.elearning.Repositories.Payments;

import com.cni.elearning.Models.Paiements.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payments,Integer> {
}
