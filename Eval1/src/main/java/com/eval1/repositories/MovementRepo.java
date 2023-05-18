package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.stock.Movement;

public interface MovementRepo extends JpaRepository<Movement, Long> {
}
