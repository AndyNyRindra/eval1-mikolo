package com.eval1.services;

import com.eval1.models.VGlobalLoss;
import com.eval1.models.VGlobalPurchases;
import com.eval1.models.sale.VGlobalSales;
import com.eval1.models.sale.VShopSales;
import com.eval1.repositories.VBeneficeMonthRepo;
import custom.springutils.service.CrudService;
import custom.springutils.util.ListResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.benef.VBeneficeMonth;

import java.util.Date;
import java.util.List;


@Service
public class VBeneficeMonthService extends CrudService<VBeneficeMonth, VBeneficeMonthRepo> {

    @Autowired
    private VGlobalSalesService globalSalesService;

    @Autowired
    private VGlobalLossService globalLossService;

    @Autowired
    private VGlobalPurchasesService globalPurchasesService;

    public VBeneficeMonthService(VBeneficeMonthRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VBeneficeMonth> getEntityClass() {
        return VBeneficeMonth.class;
    }


    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    @Override
    public String getPdfPath() {
        return "pdf/benefices-mois.pdf";
    }

    @Override
    public String getPdfTitle() {
        return "Benefices par mois";
    }

    @Override
    public int getPageSize() {
        return 12;
    }

    @Override
    public ListResponse search(Object filter, Integer page) throws Exception {
        ListResponse response = super.search(filter, page);
        List<VBeneficeMonth> beneficeMonths = (List<VBeneficeMonth>) response.getElements();
        List<VGlobalSales> globalSales = (List<VGlobalSales>) globalSalesService.search(new Object(), null).getElements();
        List<VGlobalLoss> globalLosses = (List<VGlobalLoss>) globalLossService.search(new Object(), null).getElements();
        List<VGlobalPurchases> globalPurchases = (List<VGlobalPurchases>) globalPurchasesService.search(new Object(), null).getElements();

        for (VBeneficeMonth benefice : beneficeMonths) {
            Double benef = getSales(benefice.getMois(), globalSales) + getLoss(benefice.getMois(), globalLosses) + getPurchases(benefice.getMois(), globalPurchases);
            benefice.setMontant(benef);
        }
        return response;
    }


    public Double getSales(Date month, List<VGlobalSales> globalSales) {
        for (VGlobalSales sales : globalSales) {
            if (sales.getMois().equals(month)) {
                return sales.getRecettesFinal();
            }
        }
        return 0.0;
    }

    public Double getPurchases(Date month, List<VGlobalPurchases> globalSales) {
        for (VGlobalPurchases sales : globalSales) {
            if (sales.getMois().equals(month)) {
                return sales.getDepenses();
            }
        }
        return 0.0;
    }

    public Double getLoss(Date month, List<VGlobalLoss> VGlobalLoss) {
        for (VGlobalLoss loss : VGlobalLoss) {
            if (loss.getMois().equals(month)) {
                return loss.getDepenses();
            }
        }
        return 0.0;
    }



}
