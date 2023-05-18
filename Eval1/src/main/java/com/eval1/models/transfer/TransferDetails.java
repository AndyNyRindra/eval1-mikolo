package com.eval1.models.transfer;

import com.eval1.models.laptop.Laptop;
import custom.springutils.model.HasFK;
import custom.springutils.exception.CustomException;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Double;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "transfer_details")
public class TransferDetails extends HasFK<Transfer> {

	private Double quantity;
	private Integer transferId;
	@ManyToOne()
	@JoinColumn(name = "laptop_id")
	private Laptop laptop;
    private Double unitPrice;

    @Override
    public void setFK(Transfer fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("Transfer is null");
        }
        setTransferId(fk.getId().intValue());
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
}