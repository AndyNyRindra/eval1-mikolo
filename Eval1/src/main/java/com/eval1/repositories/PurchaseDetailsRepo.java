package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.purchase.PurchaseDetails;

public interface PurchaseDetailsRepo extends JpaRepository<PurchaseDetails, Long> {
}
