package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.Loss;

public interface LossRepo extends JpaRepository<Loss, Long> {
}
