package com.eval1.models;

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
import com.eval1.models.Transfer;


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


    @Override
    public void setFK(Transfer fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("Transfer is null");
        }
        setTransferId(fk.getId().intValue());
    }
}