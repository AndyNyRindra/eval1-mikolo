package com.eval1.models.transfer;

import com.eval1.models.shop.Shop;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.String;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "transfer")
public class Transfer extends HasId {

	private String reference;
	private Timestamp date;
	@ManyToOne()
	@JoinColumn(name = "shop_receiver_id")
	private Shop shopReceiver;
	@ManyToOne()
	@JoinColumn(name = "shop_sender_id")
	private Shop shopSender;

	private Double amount;

	@Transient
	private List<TransferDetails> transferDetails;

	public void setAmount(Double amount) throws CustomException {
		if (amount < 0)
			throw new CustomException("Le montant doit être positif");
		this.amount = amount;
	}

	public void setAmount() throws CustomException {
		setAmount(this.transferDetails.stream().mapToDouble(TransferDetails::getAmount).sum());
	}

}