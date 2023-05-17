package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.brand.Brand;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand, Long> {

    List<Brand> findByName(String name);

    List<Brand> findByNameAndId(String name, Long id);
}
