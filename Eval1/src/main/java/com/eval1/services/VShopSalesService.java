package com.eval1.services;

import com.eval1.models.sale.VGlobalSales;
import com.eval1.repositories.VShopSalesRepo;
import custom.springutils.search.map.FilterInfo;
import custom.springutils.service.CrudService;
import custom.springutils.util.ListResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.sale.VShopSales;

import java.util.List;


@Service
public class VShopSalesService extends CrudService<VShopSales, VShopSalesRepo> {

    @Autowired
    private ComissionService comissionService;

    public VShopSalesService(VShopSalesRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VShopSales> getEntityClass() {
        return VShopSales.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    @Override
    public String getPdfPath() {
        return "pdf/ventes-global-mois-shop.pdf";
    }

    @Override
    public String getPdfTitle() {
        return "Ventes global par mois par point de vente";
    }

    @Override
    public ListResponse search(Object filter, Integer page) throws Exception {
        ListResponse response = super.search(filter, page);
        List<VShopSales> shopSales = (List<VShopSales>) response.getElements();
        for (VShopSales sales : shopSales) {
            sales.setComissions(comissionService.getComissions(sales.getRecettes()));
            sales.setRecettesFinal();
        }
        return response;
    }

    @Override
    public int getPageSize() {
        return 12;
    }
}
