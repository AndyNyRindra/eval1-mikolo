package com.eval1.repositories;

import com.eval1.models.transfer.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.receipt.Receipt;

import java.util.List;

public interface ReceiptRepo extends JpaRepository<Receipt, Long> {

    List<Receipt> findByReference(String reference);

    List<Receipt> findByReferenceAndId(String reference, Long id);
}
