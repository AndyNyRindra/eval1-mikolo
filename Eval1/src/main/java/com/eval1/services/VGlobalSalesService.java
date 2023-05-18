package com.eval1.services;

import com.eval1.repositories.VGlobalSalesRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.sale.VGlobalSales;


@Service
public class VGlobalSalesService extends CrudService<VGlobalSales, VGlobalSalesRepo> {

    public VGlobalSalesService(VGlobalSalesRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VGlobalSales> getEntityClass() {
        return VGlobalSales.class;
    }

}
