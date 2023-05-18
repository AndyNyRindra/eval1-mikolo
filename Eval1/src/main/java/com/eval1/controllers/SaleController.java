package com.eval1.controllers;

import com.eval1.models.shop.Shop;
import custom.springutils.controller.CrudWithFK;
import com.eval1.models.sale.Sale;
import com.eval1.services.SaleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/shops/{fkId}/sales")
public class SaleController extends CrudWithFK<Shop, com.eval1.services.ShopService, Sale, SaleService, Object> {

    public SaleController(SaleService service, com.eval1.services.ShopService fkService) {
        super(service, fkService);
    }

}
