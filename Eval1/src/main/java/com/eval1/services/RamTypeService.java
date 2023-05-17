package com.eval1.services;

import com.eval1.repositories.RamTypeRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;

import com.eval1.models.ram.RamType;


@Service
public class RamTypeService extends CrudService<RamType, RamTypeRepo> {

    public RamTypeService(RamTypeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<RamType> getEntityClass() {
        return RamType.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    boolean isRamTypeAlreadyExists(RamType ramType) throws CustomException {
        if (ramType.getId() != null) {
            if (repo.findByNameAndId(ramType.getName(), ramType.getId()).isEmpty()) {
                return isNameUsed(ramType.getName());
            }
            return false;
        }
        return isNameUsed(ramType.getName());
    }

    boolean isNameUsed(String name) throws CustomException {
        if (!repo.findByName(name).isEmpty()) {
            throw new CustomException("Ce nom est déjà utilisé");
        }
        return false;
    }

    @Override
    public RamType create(RamType obj) throws Exception {
        if (!isRamTypeAlreadyExists(obj)) {
            return super.create(obj);
        }
        return null;
    }

    @Override
    public RamType update(RamType obj) throws Exception {
        if (!isRamTypeAlreadyExists(obj)) {
            return super.update(obj);
        }
        return null;
    }

}
