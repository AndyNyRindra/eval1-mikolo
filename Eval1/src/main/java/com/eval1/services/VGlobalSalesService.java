package com.eval1.services;

import com.eval1.models.sale.VShopSales;
import com.eval1.repositories.VGlobalSalesRepo;
import custom.springutils.search.map.FilterInfo;
import custom.springutils.service.CrudService;
import custom.springutils.util.ListResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.sale.VGlobalSales;

import java.util.Date;
import java.util.List;


@Service
public class VGlobalSalesService extends CrudService<VGlobalSales, VGlobalSalesRepo> {

    @Autowired
    private ComissionService comissionService;

    @Autowired
    private VShopSalesService vShopSalesService;

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
    public ListResponse search(Object filter, Integer page) throws Exception {
        ListResponse response = super.search(filter, page);
        List<VGlobalSales> globalSales = (List<VGlobalSales>) response.getElements();
        List<VShopSales> shopSales = (List<VShopSales>) vShopSalesService.search(new Object(), null).getElements();
        for (VGlobalSales sales : globalSales) {
            sales.setRecettes(getSumRecettes(shopSales, sales.getMois()));
            sales.setComissions(getSumComissions(shopSales, sales.getMois()));
            sales.setRecettesFinal();
        }
        return response;
    }



    public Double getSumRecettes (List<VShopSales> shopSales, Date month) {
        Double sum = 0.0;
        for (VShopSales shopSale : shopSales) {
            if (shopSale.getMois().equals(month)) {
                sum += shopSale.getRecettes();
            }
        }
        return sum;
    }
    public Double getSumComissions(List<VShopSales> shopSales, Date month) {
        Double sum = 0.0;
        for (VShopSales shopSale : shopSales) {
            if (shopSale.getMois().equals(month)) {
                sum += shopSale.getComissions();
            }
        }
        return sum;
    }

    public Double getSumRecettesFinal(List<VShopSales> shopSales, Date month) {
        Double sum = 0.0;
        for (VShopSales shopSale : shopSales) {
            if (shopSale.getMois().equals(month)) {
                sum += shopSale.getRecettesFinal();
            }
        }
        return sum;
    }

    @Override
    public int getPageSize() {
        return 12;
    }
}
