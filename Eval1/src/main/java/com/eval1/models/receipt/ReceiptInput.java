package com.eval1.models.receipt;

import com.eval1.models.PurchaseSaleInput;
import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReceiptInput extends PurchaseSaleInput {

    private List<Long> transferDetails;

    public Receipt getReceipt() throws CustomException {
        Receipt receipt = new Receipt(getTransferDetails(), getQuantities());
        return receipt;
    }
}
