package com.eval1.services;

import custom.springutils.service.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import com.eval1.models.receipt.Receipt;
import com.eval1.repositories.ReceiptRepo;
import com.eval1.models.receipt.ReceiptDetails;
import com.eval1.repositories.ReceiptDetailsRepo;


@Service
public class ReceiptDetailsService extends CrudServiceWithFK<ReceiptDetails, Receipt, ReceiptRepo, ReceiptDetailsRepo> {

    public ReceiptDetailsService(ReceiptDetailsRepo repo, EntityManager manager, ReceiptRepo refRepo) {
        super(repo, manager, refRepo);
    }

    @Override
    public Object getFKValue(Receipt ref) {
        return ref.getId();
    }

    @Override
    public Class<ReceiptDetails> getEntityClass() {
        return ReceiptDetails.class;
    }

    @Override
    public String getFkName() {
        return "receiptId";
    }
}
