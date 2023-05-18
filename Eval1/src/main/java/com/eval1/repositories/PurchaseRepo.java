package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.purchase.Purchase;

import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

    List<Purchase> findByReference(String reference);

    List<Purchase> findByReferenceAndId(String reference, Long id);
}
