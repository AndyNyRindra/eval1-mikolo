package com.eval1.services;

import custom.springutils.service.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import com.eval1.models.purchase.Purchase;
import com.eval1.repositories.PurchaseRepo;
import com.eval1.models.purchase.PurchaseDetails;
import com.eval1.repositories.PurchaseDetailsRepo;


@Service
public class PurchaseDetailsService extends CrudServiceWithFK<PurchaseDetails, Purchase, PurchaseRepo, PurchaseDetailsRepo> {

    public PurchaseDetailsService(PurchaseDetailsRepo repo, EntityManager manager, PurchaseRepo refRepo) {
        super(repo, manager, refRepo);
    }

    @Override
    public Object getFKValue(Purchase ref) {
        return ref.getId();
    }

    @Override
    public Class<PurchaseDetails> getEntityClass() {
        return PurchaseDetails.class;
    }

    @Override
    public String getFkName() {
        return "purchaseId";
    }
}
