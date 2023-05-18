package com.eval1.models.receipt;

import custom.springutils.model.HasFK;
import custom.springutils.exception.CustomException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.String;
import java.sql.Timestamp;
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