package com.eval1.repositories;

import com.eval1.models.ram.RamType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.screen.ScreenType;

import java.util.List;

public interface ScreenTypeRepo extends JpaRepository<ScreenType, Long> {

    List<ScreenType> findByName(String name);

    List<ScreenType> findByNameAndId(String name, Long id);
}
