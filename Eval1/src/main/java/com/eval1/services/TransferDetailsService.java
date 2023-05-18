package com.eval1.services;

import com.eval1.models.Transfer;
import com.eval1.models.TransferDetails;
import com.eval1.repositories.TransferDetailsRepo;
import com.eval1.repositories.TransferRepo;
import custom.springutils.service.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;


@Service
public class TransferDetailsService extends CrudServiceWithFK<TransferDetails, Transfer, TransferRepo, TransferDetailsRepo> {

    public TransferDetailsService(TransferDetailsRepo repo, EntityManager manager, TransferRepo refRepo) {
        super(repo, manager, refRepo);
    }

    @Override
    public Object getFKValue(Transfer ref) {
        return ref.getId();
    }

    @Override
    public Class<TransferDetails> getEntityClass() {
        return TransferDetails.class;
    }

    @Override
    public String getFkName() {
        return "transferId";
    }
}
