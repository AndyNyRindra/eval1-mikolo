package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.VStock;

public interface VStockRepo extends JpaRepository<VStock, Long> {

    VStock findByShopIdAndLaptopId(Integer shopId, Integer laptopId);
}
