package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.comission.Comission;

public interface ComissionRepo extends JpaRepository<Comission, Long> {
}
