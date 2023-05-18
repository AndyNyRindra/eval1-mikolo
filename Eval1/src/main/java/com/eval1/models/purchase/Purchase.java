package com.eval1.models.purchase;

import com.eval1.models.laptop.Laptop;
import com.eval1.models.shop.Shop;
import custom.springutils.model.HasFK;
import custom.springutils.exception.CustomException;
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
@Table(name = "purchase")
public class Purchase extends HasFK<Shop> {

	private String reference;
	private Timestamp date;
	@ManyToOne()
	@JoinColumn(name = "shop_id")
	private Shop shop;
    private Double amount;

    @Transient
    private List<PurchaseDetails> purchaseDetails;


    public Purchase(List<Long> laptopsId, List<Double> quantities) throws CustomException {
        if (laptopsId.size() != quantities.size()) {
            throw new CustomException("Les listes de laptops et de quantités doivent être de même taille");
        }
        setPurchaseDetails(new ArrayList<>());
        for (int i = 0; i < laptopsId.size(); i++) {
            PurchaseDetails purchaseDetails = new PurchaseDetails();
            Laptop laptop = new Laptop();
            laptop.setId(laptopsId.get(i));
            purchaseDetails.setLaptop(laptop);
            purchaseDetails.setQuantity(quantities.get(i));
            this.purchaseDetails.add(purchaseDetails);
        }
    }

    public Purchase() {

    }


    @Override
    public void setFK(Shop fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("Shop is null");
        }
        setShop(fk);
    }

    public void setAmount(Double amount) throws CustomException {
        if (amount < 0)
            throw new CustomException("Le montant doit être positif");
        this.amount = amount;
    }
    public void setAmount() throws CustomException {
        setAmount(this.purchaseDetails.stream().mapToDouble(PurchaseDetails::getAmount).sum());
    }
}