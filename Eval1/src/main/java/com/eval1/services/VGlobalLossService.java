package com.eval1.services;

import com.eval1.repositories.VGlobalLossRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Double;
import java.lang.Long;
import java.sql.Date;
import com.eval1.models.VGlobalLoss;


@Service
public class VGlobalLossService extends CrudService<VGlobalLoss, VGlobalLossRepo> {

    public VGlobalLossService(VGlobalLossRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VGlobalLoss> getEntityClass() {
        return VGlobalLoss.class;
    }

}
