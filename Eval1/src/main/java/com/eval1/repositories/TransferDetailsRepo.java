package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.TransferDetails;

public interface TransferDetailsRepo extends JpaRepository<TransferDetails, Long> {
}
