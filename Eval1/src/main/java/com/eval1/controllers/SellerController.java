package com.eval1.controllers;

import com.eval1.models.seller.Seller;
import com.eval1.models.seller.SellerFilter;
import com.eval1.models.shop.Shop;
import com.eval1.security.SecurityManager;
import com.eval1.services.SellerService;
import com.eval1.services.ShopService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    SellerService sellerService;

    @Autowired
    ShopService shopService;

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        Seller seller = sellerService.findById(id);
        modelAndView.addObject("seller", seller);
        modelAndView.addObject("shops", shopService.findAll());
        modelAndView.setViewName("sellers/update-seller-shop");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@RequestParam(name = "shopId", required = false) Long shopid, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            Seller seller = sellerService.findById(id);
            if (shopid != null) {
                Shop shop = shopService.findById(shopid);
                seller.setShop(shop);
            } else {
                seller.setShop(null);
            }

            sellerService.update(seller);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, SellerFilter sellerFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse sellers = sellerService.search(new Object(), page);
        modelAndView.addObject("requiredPages", sellerService.getRequiredPages(sellers.getCount()));
        modelAndView.addObject("sellers",sellers);
        modelAndView.addObject("page", page);
        if (sellerFilter != null) modelAndView.addObject("sellerFilter", sellerFilter);
        modelAndView.setViewName("sellers/list-sellers");
        return modelAndView;
    }
}
