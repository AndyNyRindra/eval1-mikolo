package com.eval1.services;

import com.eval1.repositories.VGlobalPurchasesRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Double;
import java.lang.Long;
import java.sql.Date;
import com.eval1.models.VGlobalPurchases;


@Service
public class VGlobalPurchasesService extends CrudService<VGlobalPurchases, VGlobalPurchasesRepo> {

    public VGlobalPurchasesService(VGlobalPurchasesRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VGlobalPurchases> getEntityClass() {
        return VGlobalPurchases.class;
    }

}
