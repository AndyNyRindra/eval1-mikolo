package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.receipt.ReceiptDetails;

public interface ReceiptDetailsRepo extends JpaRepository<ReceiptDetails, Long> {
}
