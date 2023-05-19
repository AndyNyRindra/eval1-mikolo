package com.eval1.controllers;

import com.eval1.models.purchase.Purchase;
import com.eval1.models.purchase.PurchaseInput;
import com.eval1.models.receipt.ReceiptInput;
import com.eval1.models.seller.Seller;
import com.eval1.models.shop.Shop;
import com.eval1.security.SecurityManager;
import custom.springutils.controller.CrudWithFK;
import com.eval1.models.receipt.Receipt;
import com.eval1.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.eval1.services.TransferService;
import com.eval1.models.transfer.Transfer;


@Controller
public class ReceiptController  {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer/{id}/receipts")
    public ResponseEntity<?> save(@ModelAttribute ReceiptInput receiptInput, @PathVariable Long id) throws Exception {
        securityManager.isConnected();
        try {
            Transfer transfer = transferService.findById(id);

            Receipt receipt = receiptInput.getReceipt();
            receipt.setTransfer(transfer);
            receiptService.create(receipt);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
