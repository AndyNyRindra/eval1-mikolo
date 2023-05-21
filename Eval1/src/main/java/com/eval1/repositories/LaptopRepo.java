package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.laptop.Laptop;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {

    List<Laptop> findByReference(String reference);

    List<Laptop> findByReferenceAndId(String reference, Long id);

    @Query(value = "select l.* from laptop l join v_stock vs on l.id = vs.laptop_id where shop_id =?1 and quantity > 0", nativeQuery = true)
    List<Laptop> findAvailableByShopId(Long id);
}
