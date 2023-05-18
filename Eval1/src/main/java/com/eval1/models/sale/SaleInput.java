package com.eval1.models.sale;

import com.eval1.models.PurchaseSaleInput;
import com.eval1.models.purchase.Purchase;
import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleInput extends PurchaseSaleInput {

    public Sale getSale() throws CustomException {
        Sale sale = new Sale(getLaptops(), getQuantities());
        sale.setReference(getReference());
        sale.setDate(getDate());
        return sale;
    }
}
