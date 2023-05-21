package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.comission.Comission;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComissionRepo extends JpaRepository<Comission, Long> {


    @Query(value = "select * from comission order by min_value asc", nativeQuery = true)
    List<Comission> findAllOrder();
}
