package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.sale.SaleDetails;

public interface SaleDetailsRepo extends JpaRepository<SaleDetails, Long> {
}
