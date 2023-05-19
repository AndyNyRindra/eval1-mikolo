package com.eval1.services;

import com.eval1.models.Loss;
import com.eval1.models.receipt.ReceiptDetails;
import com.eval1.models.stock.Movement;
import com.eval1.models.transfer.TransferDetails;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.String;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.eval1.models.transfer.Transfer;
import com.eval1.repositories.TransferRepo;
import com.eval1.models.receipt.Receipt;
import com.eval1.repositories.ReceiptRepo;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReceiptService extends CrudServiceWithFK<Receipt, Transfer, TransferRepo, ReceiptRepo> {

    @Autowired
    private MovementService movementService;

    @Autowired
    private TransferDetailsService transferDetailsService;

    @Autowired
    private ReceiptDetailsService receiptDetailsService;

    @Autowired
    private LossService lossService;

    @Autowired
    private TransferService transferService;

    public ReceiptService(ReceiptRepo repo, EntityManager manager, TransferRepo refRepo) {
        super(repo, manager, refRepo);
    }

    @Override
    public Object getFKValue(Transfer ref) {
        return ref;
    }

    @Override
    public Class<Receipt> getEntityClass() {
        return Receipt.class;
    }

    @Override
    public String getFkName() {
        return "transfer";
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    boolean isReceiptAlreadyExists(Receipt receipt) throws CustomException {
        if (receipt.getId() != null) {
            if (repo.findByReferenceAndId(receipt.getReference(), receipt.getId()).isEmpty()) {
                return isReferenceUsed(receipt.getReference());
            }
            return false;
        }
        return isReferenceUsed(receipt.getReference());
    }

    boolean isReferenceUsed(String ref) throws CustomException {
        if (!repo.findByReference(ref).isEmpty()) {
            throw new CustomException("Cette référence est déjà utilisée");
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Receipt create(Receipt obj) throws Exception {
        if (!isReceiptAlreadyExists(obj)) {

            obj.getTransfer().setStatus(1);
            obj.setReference(obj.getTransfer().getReference());
            transferService.update(obj.getTransfer());
            obj.setDate(Timestamp.valueOf(LocalDateTime.now()));
            obj.setAmount((double) 1);
            Receipt toSave = super.create(obj);
            for (ReceiptDetails receiptDetails : obj.getReceiptDetails()) {
                receiptDetails.setReceiptId(toSave.getId().intValue());
                TransferDetails transferDetails = transferDetailsService.findById(receiptDetails.getTransferDetails().getId());
                receiptDetails.setUnitPrice(transferDetails.getUnitPrice());
                receiptDetails.setTransferDetails(transferDetails);
                Movement movement = new Movement(receiptDetails, obj.getTransfer().getShopReceiver().getId().intValue(), obj.getDate());
                movementService.create(movement);
                receiptDetailsService.create(receiptDetails);
                if (receiptDetails.getQuantityNotDelivered() > 0) {
                    Loss loss = new Loss(receiptDetails, obj.getDate());
                    lossService.create(loss);
                }
            }
            obj.setAmount();
            return super.create(obj);
        }
        return null;
    }
}
