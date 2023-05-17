package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
