package com.eval1.controllers;

import com.eval1.models.shop.Shop;
import custom.springutils.controller.CrudWithFK;
import com.eval1.models.purchase.Purchase;
import com.eval1.services.PurchaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eval1.services.ShopService;


@RestController
@RequestMapping("/shops/{fkId}/purchases")
public class PurchaseController extends CrudWithFK<Shop, ShopService, Purchase, PurchaseService, Object> {

    public PurchaseController(PurchaseService service, ShopService fkService) {
        super(service, fkService);
    }

}
