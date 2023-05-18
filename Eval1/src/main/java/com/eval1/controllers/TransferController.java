package com.eval1.controllers;

import custom.springutils.controller.CrudController;
import com.eval1.services.TransferService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eval1.models.transfer.Transfer;

@RestController
@RequestMapping("/transfers")
public class TransferController extends CrudController<Transfer, TransferService, Object> {

    public TransferController(TransferService service) {
        super(service);
    }

}
