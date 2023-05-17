package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.laptop.Laptop;

import java.util.List;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {

    List<Laptop> findByReference(String reference);

    List<Laptop> findByReferenceAndId(String reference, Long id);
}
