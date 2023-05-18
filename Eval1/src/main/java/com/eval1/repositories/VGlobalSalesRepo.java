package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.sale.VGlobalSales;

public interface VGlobalSalesRepo extends JpaRepository<VGlobalSales, Long> {
}
