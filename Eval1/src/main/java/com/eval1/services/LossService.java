package com.eval1.services;

import com.eval1.repositories.LossRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.lang.Double;
import java.lang.Integer;
import com.eval1.models.Loss;


@Service
public class LossService extends CrudService<Loss, LossRepo> {

    public LossService(LossRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Loss> getEntityClass() {
        return Loss.class;
    }

}
