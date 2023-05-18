package com.eval1.models.purchase;

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
@Table(name = "purchase_details")
public class PurchaseDetails extends HasFK<Purchase> {

	private Double quantity;
	private Integer purchaseId;
	@ManyToOne()
	@JoinColumn(name = "laptop_id")
	private Laptop laptop;
    private Double amount;

    @Override
    public void setFK(Purchase fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("Purchase is null");
        }
        setPurchaseId(fk.getId().intValue());
    }

    public void setQuantity(Double quantity) throws CustomException {
        if (quantity == null || quantity <= 0) {
            throw new CustomException("La quantité est nulle ou négative");
        }
        this.quantity = quantity;
    }

    public void setAmount() throws CustomException {
        	setAmount(this.quantity * this.laptop.getPrice());
    }

    public void setAmount(Double amount) throws CustomException {
        if (amount == null || amount <= 0) {
            throw new CustomException("Le montant est nul ou négatif");
        }
        this.amount = amount;
    }
}