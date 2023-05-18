package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.sale.VShopSales;

public interface VShopSalesRepo extends JpaRepository<VShopSales, Long> {
}
