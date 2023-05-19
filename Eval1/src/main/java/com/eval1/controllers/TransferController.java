package com.eval1.controllers;

import com.eval1.models.ActionType;
import com.eval1.models.purchase.Purchase;
import com.eval1.models.purchase.PurchaseInput;
import com.eval1.models.sale.Sale;
import com.eval1.models.sale.SaleFilter;
import com.eval1.models.seller.Seller;
import com.eval1.models.shop.Shop;
import com.eval1.models.transfer.TransferFilter;
import com.eval1.models.transfer.TransferInput;
import com.eval1.security.SecurityManager;
import com.eval1.services.LaptopService;
import com.eval1.services.ShopService;
import com.eval1.services.TransferDetailsService;
import custom.springutils.controller.CrudController;
import com.eval1.services.TransferService;
import custom.springutils.util.ListResponse;
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

    @Autowired
    private TransferService service;

    @Autowired
    private TransferDetailsService transferDetailsService;

    @GetMapping("/create")
    public ModelAndView create(ModelAndView model) throws Exception {
        securityManager.isAdmin();
        model.addObject("shops",shopService.findByRoleSeller());
        model.addObject("laptops",laptopService.findAvailableByShopId( ((Seller) session.getAttribute("connected")).getShop().getId()));
        model.setViewName("transfers/create-transfer");
        return model;
    }

    @GetMapping("/return/create")
    public ModelAndView createReturn(ModelAndView model) throws Exception {
        securityManager.isSeller();
        model.addObject("laptops",laptopService.findAvailableByShopId( ((Seller) session.getAttribute("connected")).getShop().getId()));
        model.setViewName("returns/create-return");
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


    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, TransferFilter transferFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isConnected();
        if (page == null) page = 1;
        Seller connected = (Seller) session.getAttribute("connected");
        ListResponse transfers = transferService.search(transferFilter, page);
        modelAndView.addObject("requiredPages", transferService.getRequiredPages(transfers.getCount()));
        modelAndView.addObject("transfers",transfers);
        modelAndView.addObject("page", page);
        if (transferFilter != null) modelAndView.addObject("transferFilter", transferFilter);
        modelAndView.setViewName("transfers/list-transfers");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView details(ModelAndView modelAndView, @PathVariable Long id) throws Exception {
        securityManager.isConnected();
        Transfer transfer = transferService.findById(id);
        ListResponse details = transferDetailsService.search(new Object(), id, null);
        modelAndView.addObject("details",details);
        modelAndView.addObject("transfer", transfer);
        modelAndView.setViewName("transfers/sheet-transfer");
        return modelAndView;
    }
}
