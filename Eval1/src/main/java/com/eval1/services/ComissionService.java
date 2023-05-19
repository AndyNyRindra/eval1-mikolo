package com.eval1.services;

import com.eval1.repositories.ComissionRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.comission.Comission;


@Service
public class ComissionService extends CrudService<Comission, ComissionRepo> {

    public ComissionService(ComissionRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Comission> getEntityClass() {
        return Comission.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


}
