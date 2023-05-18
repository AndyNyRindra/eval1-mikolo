package com.eval1.services;

import custom.springutils.service.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import com.eval1.models.sale.Sale;
import com.eval1.repositories.SaleRepo;
import com.eval1.models.sale.SaleDetails;
import com.eval1.repositories.SaleDetailsRepo;


@Service
public class SaleDetailsService extends CrudServiceWithFK<SaleDetails, Sale, SaleRepo, SaleDetailsRepo> {

    public SaleDetailsService(SaleDetailsRepo repo, EntityManager manager, SaleRepo refRepo) {
        super(repo, manager, refRepo);
    }

    @Override
    public Object getFKValue(Sale ref) {
        return ref.getId();
    }

    @Override
    public Class<SaleDetails> getEntityClass() {
        return SaleDetails.class;
    }

    @Override
    public String getFkName() {
        return "saleId";
    }
}
