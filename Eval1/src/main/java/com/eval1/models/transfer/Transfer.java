package com.eval1.models.transfer;

import com.eval1.models.laptop.Laptop;
import com.eval1.models.shop.Shop;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.String;
import java.sql.Timestamp;
import java.util.ArrayList;
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

	public Transfer(List<Long> laptops, List<Double> quantities, Long shopReceiverId) throws CustomException {
		if (laptops.size() != quantities.size()) {
			throw new CustomException("Les quantités et les laptops doivent être de même taille");
		}
		Shop shopReceiver = new Shop();
		shopReceiver.setId(shopReceiverId);
		setShopReceiver(shopReceiver);
		setTransferDetails(new ArrayList<>());
		for (int i = 0; i < laptops.size(); i++) {
			TransferDetails transferDetails = new TransferDetails();
			Laptop laptop = new Laptop();
			laptop.setId(laptops.get(i));
			transferDetails.setLaptop(laptop);
			transferDetails.setQuantity(quantities.get(i));
			this.transferDetails.add(transferDetails);
		}
	}

	public Transfer() {

	}

	public void setAmount(Double amount) throws CustomException {
		if (amount < 0)
			throw new CustomException("Le montant doit être positif");
		this.amount = amount;
	}

	public void setAmount() throws CustomException {
		setAmount(this.transferDetails.stream().mapToDouble(TransferDetails::getAmount).sum());
	}

}