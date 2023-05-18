package com.eval1.models.receipt;

import custom.springutils.model.HasFK;
import custom.springutils.exception.CustomException;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.eval1.models.transfer.TransferDetails;
import java.lang.Double;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "receipt_details")
public class ReceiptDetails extends HasFK<Receipt> {

	@ManyToOne()
	@JoinColumn(name = "transfer_details_id")
	private TransferDetails transferDetails;
	private Double quantity;
	private Integer receiptId;
    private Double unitPrice;


    @Override
    public void setFK(Receipt fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("Receipt is null");
        }
        setReceiptId(fk.getId().intValue());
    }

    public void setQuantity(Double quantity) throws CustomException {
        if (quantity == null || quantity <= 0) {
            throw new CustomException("La quantité est nulle ou négative");
        }
        this.quantity = quantity;
    }

    public double getAmount()  {
        return this.quantity * this.unitPrice;
    }

    public void setUnitPrice(Double amount) throws CustomException {
        if (amount == null || amount <= 0) {
            throw new CustomException("Le montant est nul ou négatif");
        }
        this.unitPrice = amount;
    }

    public Double getQuantityNotDelivered() {
        return transferDetails.getQuantity() - quantity;
    }
}