package com.eval1.models.purchase;

import com.eval1.models.shop.Shop;
import custom.springutils.model.HasFK;
import custom.springutils.exception.CustomException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.String;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "purchase")
public class Purchase extends HasFK<Shop> {

	private String reference;
	private Timestamp date;
	@ManyToOne()
	@JoinColumn(name = "shop_id")
	private Shop shop;

    @Transient
    private List<PurchaseDetails> purchaseDetails;


    @Override
    public void setFK(Shop fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("Shop is null");
        }
        setShop(fk);
    }
}