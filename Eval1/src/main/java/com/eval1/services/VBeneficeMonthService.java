package com.eval1.services;

import com.eval1.repositories.VBeneficeMonthRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Double;
import java.lang.Long;
import java.sql.Date;
import com.eval1.models.VBeneficeMonth;


@Service
public class VBeneficeMonthService extends CrudService<VBeneficeMonth, VBeneficeMonthRepo> {

    public VBeneficeMonthService(VBeneficeMonthRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VBeneficeMonth> getEntityClass() {
        return VBeneficeMonth.class;
    }

}
