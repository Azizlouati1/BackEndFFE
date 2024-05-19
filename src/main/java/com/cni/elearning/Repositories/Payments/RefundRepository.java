package com.cni.elearning.Repositories.Payments;

import com.cni.elearning.Models.Paiements.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund,Integer> {
}
