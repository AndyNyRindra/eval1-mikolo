package com.eval1.models.sale;

import com.eval1.models.purchase.PurchaseDetails;
import com.eval1.models.shop.Shop;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasFK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

import static com.eval1.util.DateUtil.formatDate;


@Getter
@Setter
@Entity
@Table(name = "sale")
public class Sale extends HasFK<Shop> {

	private String reference;
	private Timestamp date;
	private Double amount;
	@ManyToOne()
	@JoinColumn(name = "shop_id")
	private Shop shop;

    @Transient
    private List<SaleDetails> saleDetails;


    @Override
    public void setFK(Shop fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("com.eval1.models.Shop is null");
        }
        setShop(fk);
    }

    public void setAmount(Double amount) throws CustomException {
        if (amount < 0)
            throw new CustomException("Le montant doit être positif");
        this.amount = amount;
    }
    public void setAmount() throws CustomException {
        setAmount(this.saleDetails.stream().mapToDouble(SaleDetails::getAmount).sum());
    }

    public String getDateToStr () {
        return formatDate(getDate());
    }
}