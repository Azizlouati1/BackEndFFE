package com.cni.elearning.Repositories.Payments;

import com.cni.elearning.Models.Paiements.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payments,Integer> {
    @Query("select p from Payments p where p.data.transaction_id =:transaction_id")
    Payments getPaymentsByDataTransaction_id(@Param("transaction_id") int transaction_id);
}
