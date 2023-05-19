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

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    @Override
    public String getPdfPath() {
        return "pdf/ventes-global-mois.pdf";
    }

    @Override
    public String getPdfTitle() {
        return "Ventes global par mois";
    }


}
