package com.eval1.models.purchase;

import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class PurchaseInput {

    private String reference;
    private Timestamp date;
    List<Long> laptops;
    List<Double> quantities;

    public Purchase getPurchase() throws CustomException {
        Purchase purchase = new Purchase(laptops, quantities);
        purchase.setReference(reference);
        purchase.setDate(date);
        return purchase;
    }

}
