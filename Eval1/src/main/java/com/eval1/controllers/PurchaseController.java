package com.eval1.controllers;

import com.eval1.models.ActionType;
import com.eval1.models.laptop.Laptop;
import com.eval1.models.purchase.PurchaseInput;
import com.eval1.models.ram.RamType;
import com.eval1.models.seller.Seller;
import com.eval1.models.shop.Shop;
import com.eval1.security.SecurityManager;
import com.eval1.services.LaptopService;
import custom.springutils.controller.CrudWithFK;
import com.eval1.models.purchase.Purchase;
import com.eval1.services.PurchaseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.eval1.services.ShopService;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/purchases")
public class PurchaseController  {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private HttpSession session;

    @GetMapping("/create")
    public ModelAndView create(ModelAndView model) throws Exception {
        securityManager.isAdmin();
        model.addObject("laptops",laptopService.findAll());
        model.addObject("actionType", new ActionType("Achat","purchases"));
        model.setViewName("purchases/create-purchase");
        return model;
    }


    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute PurchaseInput purchaseInput) throws Exception {
        securityManager.isAdmin();
        try {
            Purchase purchase = purchaseInput.getPurchase();
            Seller seller = (Seller) session.getAttribute("connected");
            Shop shop = seller.getShop();
            purchase.setFK(shop);
            purchaseService.create(purchase);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
