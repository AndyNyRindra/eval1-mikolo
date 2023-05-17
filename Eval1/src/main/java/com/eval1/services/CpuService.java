package com.eval1.services;

import com.eval1.models.ram.RamType;
import com.eval1.repositories.CpuRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.cpu.Cpu;


@Service
public class CpuService extends CrudService<Cpu, CpuRepo> {

    public CpuService(CpuRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Cpu> getEntityClass() {
        return Cpu.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    boolean isCpuAlreadyExists(Cpu cpu) throws CustomException {
        if (cpu.getId() != null) {
            if (repo.findByNameAndId(cpu.getName(), cpu.getId()).isEmpty()) {
                return isNameUsed(cpu.getName());
            }
            return false;
        }
        return isNameUsed(cpu.getName());
    }

    boolean isNameUsed(String name) throws CustomException {
        if (!repo.findByName(name).isEmpty()) {
            throw new CustomException("Ce nom est déjà utilisé");
        }
        return false;
    }

    @Override
    public Cpu create(Cpu obj) throws Exception {
        if (!isCpuAlreadyExists(obj)) {
            return super.create(obj);
        }
        return null;
    }

    @Override
    public Cpu update(Cpu obj) throws Exception {
        if (!isCpuAlreadyExists(obj)) {
            return super.update(obj);
        }
        return null;
    }

}
