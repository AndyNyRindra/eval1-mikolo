package com.eval1.services;

import com.eval1.repositories.VBeneficeMonthRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.benef.VBeneficeMonth;


@Service
public class VBeneficeMonthService extends CrudService<VBeneficeMonth, VBeneficeMonthRepo> {

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

}
