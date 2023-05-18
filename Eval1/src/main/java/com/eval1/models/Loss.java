package com.eval1.models;

import com.eval1.models.receipt.ReceiptDetails;
import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;
import java.lang.Double;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "loss")
public class Loss extends HasId {

	private Timestamp date;
	private Double amount;
	private Double quantity;
	private Integer receiptDetailsId;

	public Loss (ReceiptDetails receiptDetails, Timestamp date) {
		setDate(date);
		setAmount(receiptDetails.getQuantityNotDelivered() * receiptDetails.getUnitPrice());
		setQuantity(receiptDetails.getQuantityNotDelivered());
		setReceiptDetailsId(receiptDetails.getId().intValue());
	}

	public Loss() {

	}
}