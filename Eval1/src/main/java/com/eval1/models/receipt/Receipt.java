package com.eval1.models.receipt;

import com.eval1.models.transfer.TransferDetails;
import custom.springutils.model.HasFK;
import custom.springutils.exception.CustomException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.String;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.eval1.models.transfer.Transfer;


@Getter
@Setter
@Entity
@Table(name = "receipt")
public class Receipt extends HasFK<Transfer> {

	private String reference;
	private Timestamp date;
	@ManyToOne()
	@JoinColumn(name = "transfer_id")
	private Transfer transfer;
    private Double amount;

    @Transient
    private List<ReceiptDetails> receiptDetails;

    public Receipt(List<Long> transferDetailsId, List<Double> quantities) throws CustomException {
        if(transferDetailsId.size() != quantities.size()) {
            throw new CustomException("transferDetailsId and amounts must have the same size");
        }
        setReceiptDetails(new ArrayList<>());
        for(int i = 0; i < transferDetailsId.size(); i++) {
            ReceiptDetails receiptDetails = new ReceiptDetails();
            TransferDetails transferDetails = new TransferDetails();
            transferDetails.setId(transferDetailsId.get(i));
            receiptDetails.setTransferDetails(transferDetails);
            receiptDetails.setQuantity(quantities.get(i));
            this.receiptDetails.add(receiptDetails);
        }
    }


    @Override
    public void setFK(Transfer fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("Transfer is null");
        }
        setTransfer(fk);
    }

    public void setAmount(Double amount) throws CustomException {
        if (amount == null || amount <= 0) {
            throw new CustomException("Le montant est nul ou négatif");
        }
        this.amount = amount;
    }

    public void setAmount() throws CustomException {
    	setAmount(this.receiptDetails.stream().mapToDouble(ReceiptDetails::getAmount).sum());
    }
}