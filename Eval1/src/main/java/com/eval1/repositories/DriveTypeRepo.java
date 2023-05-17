package com.eval1.repositories;

import com.eval1.models.ram.RamType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.drive.DriveType;

import java.util.List;

public interface DriveTypeRepo extends JpaRepository<DriveType, Long> {

    List<DriveType> findByName(String name);

    List<DriveType> findByNameAndId(String name, Long id);
}
