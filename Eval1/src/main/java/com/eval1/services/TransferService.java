package com.eval1.services;

import com.eval1.models.Movement;
import com.eval1.models.Transfer;
import com.eval1.models.TransferDetails;
import com.eval1.models.VStock;
import com.eval1.models.purchase.Purchase;
import com.eval1.repositories.TransferRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class TransferService extends CrudService<Transfer, TransferRepo> {

    @Autowired
    private TransferDetailsService transferDetailsService;

    @Autowired
    private MovementService movementService;

    @Autowired
    private VStockService vStockService;

    public TransferService(TransferRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Transfer> getEntityClass() {
        return Transfer.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    boolean isTransferAlreadyExists(Transfer transfer) throws CustomException {
        if (transfer.getId() != null) {
            if (repo.findByReferenceAndId(transfer.getReference(), transfer.getId()).isEmpty()) {
                return isReferenceUsed(transfer.getReference());
            }
            return false;
        }
        return isReferenceUsed(transfer.getReference());
    }

    boolean isReferenceUsed(String ref) throws CustomException {
        if (!repo.findByReference(ref).isEmpty()) {
            throw new CustomException("Cette référence est déjà utilisée");
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Transfer create(Transfer obj) throws Exception {
        if (!isTransferAlreadyExists(obj)) {
            obj.setDate(Timestamp.valueOf(LocalDateTime.now()));
            Transfer toSave = super.create(obj);
            for (TransferDetails transferDetails : obj.getTransferDetails()) {
                vStockService.isLaptopQuantityEnough(obj.getShopSender().getId().intValue(), transferDetails.getLaptop().getId().intValue(), transferDetails.getQuantity());
                transferDetails.setTransferId(toSave.getId().intValue());
                Movement movement = new Movement(transferDetails, obj.getShopSender().getId().intValue(), obj.getDate());
                movementService.create(movement);
                transferDetailsService.create(transferDetails);
            }
            toSave.setTransferDetails(obj.getTransferDetails());
            return toSave;
        }
        return null;
    }
}
