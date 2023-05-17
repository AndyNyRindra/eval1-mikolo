package com.eval1.repositories;

import com.eval1.models.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.ram.RamType;

import java.util.List;

public interface RamTypeRepo extends JpaRepository<RamType, Long> {

    List<RamType> findByName(String name);

    List<RamType> findByNameAndId(String name, Long id);
}
