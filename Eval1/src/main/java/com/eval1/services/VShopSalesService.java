package com.eval1.services;

import com.eval1.repositories.VShopSalesRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.sale.VShopSales;


@Service
public class VShopSalesService extends CrudService<VShopSales, VShopSalesRepo> {

    public VShopSalesService(VShopSalesRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VShopSales> getEntityClass() {
        return VShopSales.class;
    }

}
