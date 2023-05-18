package com.eval1.models.transfer;

import com.eval1.models.PurchaseSaleInput;
import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransferInput extends PurchaseSaleInput {

    private Long shopReceiverId;

    public Transfer getTransfer() throws CustomException {
        Transfer transfer = new Transfer(getLaptops(), getQuantities(), getShopReceiverId());
        transfer.setReference(getReference());
        transfer.setDate(getDate());
        return transfer;
    }
}
