package com.eval1.models.sale;

import com.eval1.models.laptop.Laptop;
import com.eval1.models.purchase.PurchaseDetails;
import com.eval1.models.shop.Shop;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasFK;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    public Sale() {
    }

    public Sale(List<Long> laptops, List<Double> quantities) throws CustomException {
        if (laptops.size() != quantities.size()) {
            throw new CustomException("Les quantités et les laptops doivent être de même taille");
        }
        setSaleDetails(new ArrayList<>());
        for (int i = 0; i < laptops.size(); i++) {
            SaleDetails details = new SaleDetails();
            Laptop laptop = new Laptop();
            laptop.setId(laptops.get(i));
            details.setLaptop(laptop);
            details.setQuantity(quantities.get(i));
            this.getSaleDetails().add(details);
        }
    }


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