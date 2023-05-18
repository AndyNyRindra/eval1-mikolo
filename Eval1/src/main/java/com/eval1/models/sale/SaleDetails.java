package com.eval1.models.sale;

import com.eval1.models.laptop.Laptop;
import custom.springutils.model.HasFK;
import custom.springutils.exception.CustomException;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Integer;
import java.lang.Double;


@Getter
@Setter
@Entity
@Table(name = "sale_details")
public class SaleDetails extends HasFK<Sale> {

	private Integer saleId;
	private Double quantity;
	private Double unitPrice;
	@ManyToOne()
	@JoinColumn(name = "laptop_id")
	private Laptop laptop;


    @Override
    public void setFK(Sale fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("com.eval1.models.sale.Sale is null");
        }
        setSaleId(fk.getId().intValue());
    }

    public Double getAmount() {
        return this.quantity * this.unitPrice;
    }

    public void setUnitPrice(Double amount) throws CustomException {
        if (amount == null || amount <= 0) {
            throw new CustomException("Le montant est nul ou négatif");
        }
        this.unitPrice = amount;
    }
}