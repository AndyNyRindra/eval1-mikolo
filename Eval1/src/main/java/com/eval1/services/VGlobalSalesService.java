package com.eval1.services;

import com.eval1.repositories.VGlobalSalesRepo;
import custom.springutils.search.map.FilterInfo;
import custom.springutils.service.CrudService;
import custom.springutils.util.ListResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.sale.VGlobalSales;

import java.util.List;


@Service
public class VGlobalSalesService extends CrudService<VGlobalSales, VGlobalSalesRepo> {

    @Autowired
    private ComissionService comissionService;

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

    @Override
    protected ListResponse search(Object filter, FilterInfo criteria, Integer page) throws Exception {
        ListResponse response = super.search(filter, criteria, page);
        List<VGlobalSales> globalSales = (List<VGlobalSales>) response.getElements();
        for (VGlobalSales globalSale : globalSales) {
            globalSale.setComissions(comissionService.getComissions(globalSale.getRecettes()));
            globalSale.setRecettesFinal();
        }
        return response;
    }
}
