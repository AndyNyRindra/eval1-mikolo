package com.eval1.repositories;

import com.eval1.models.ram.RamType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.cpu.Cpu;

import java.util.List;

public interface CpuRepo extends JpaRepository<Cpu, Long> {

    List<Cpu> findByName(String name);

    List<Cpu> findByNameAndId(String name, Long id);
}
