package com.eval1.models;

import com.eval1.models.shop.Shop;
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

	@Transient
	private List<TransferDetails> transferDetails;

}