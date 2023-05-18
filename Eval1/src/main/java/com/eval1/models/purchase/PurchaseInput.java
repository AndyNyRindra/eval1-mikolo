package com.eval1.models.purchase;

import com.eval1.models.PurchaseSaleInput;
import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class PurchaseInput extends PurchaseSaleInput {

    public Purchase getPurchase() throws CustomException {
        Purchase purchase = new Purchase(getLaptops(), getQuantities());
        purchase.setReference(getReference());
        purchase.setDate(getDate());
        return purchase;
    }

}
