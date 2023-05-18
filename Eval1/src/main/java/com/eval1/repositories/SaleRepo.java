package com.eval1.repositories;

import com.eval1.models.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.sale.Sale;

import java.util.List;

public interface SaleRepo extends JpaRepository<Sale, Long> {

    List<Sale> findByReference(String reference);

    List<Sale> findByReferenceAndId(String reference, Long id);
}
