package com.eval1.controllers;

import custom.springutils.controller.CrudWithFK;
import com.eval1.models.receipt.Receipt;
import com.eval1.services.ReceiptService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eval1.services.TransferService;
import com.eval1.models.transfer.Transfer;


@RestController
@RequestMapping("/transfers/{fkId}/receipts")
public class ReceiptController extends CrudWithFK<Transfer, com.eval1.services.TransferService, Receipt, ReceiptService, Object> {

    public ReceiptController(ReceiptService service, TransferService fkService) {
        super(service, fkService);
    }

}
