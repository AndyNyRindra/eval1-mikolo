package com.eval1.controllers;

import com.eval1.models.ActionType;
import com.eval1.models.purchase.Purchase;
import com.eval1.models.purchase.PurchaseInput;
import com.eval1.models.seller.Seller;
import com.eval1.models.shop.Shop;
import com.eval1.models.transfer.TransferInput;
import com.eval1.security.SecurityManager;
import com.eval1.services.LaptopService;
import com.eval1.services.ShopService;
import custom.springutils.controller.CrudController;
import com.eval1.services.TransferService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.eval1.models.transfer.Transfer;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private HttpSession session;

    @Autowired
    private ShopService shopService;

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private LaptopService laptopService;

    @GetMapping("/create")
    public ModelAndView create(ModelAndView model) throws Exception {
        securityManager.isAdmin();
        model.addObject("shops",shopService.findByRoleSeller());
        model.addObject("laptops",laptopService.findAvailableByShopId( ((Seller) session.getAttribute("connected")).getShop().getId()));
        model.setViewName("transfers/create-transfer");
        return model;
    }


    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute TransferInput transferInput) throws Exception {
        securityManager.isConnected();
        try {
            if (session.getAttribute("seller") != null) {
                transferInput.setShopReceiverId(1L);
            }
            Transfer transfer = transferInput.getTransfer();
            Seller seller = (Seller) session.getAttribute("connected");

            Shop shop = seller.getShop();
            transfer.setShopSender(shop);
            transferService.create(transfer);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
