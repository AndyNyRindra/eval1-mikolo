package com.eval1.controllers;

import com.eval1.models.drive.DriveFilter;
import com.eval1.models.sale.SaleFilter;
import com.eval1.models.seller.Seller;
import com.eval1.models.shop.Shop;
import com.eval1.security.SecurityManager;
import com.eval1.services.SaleDetailsService;
import com.eval1.services.ShopService;
import custom.springutils.controller.CrudWithFK;
import com.eval1.models.sale.Sale;
import com.eval1.services.SaleService;
import custom.springutils.util.ListResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sales")
public class SaleController  {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleDetailsService saleDetailsService;

    @Autowired
    private HttpSession session;

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, SaleFilter saleFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isSeller();
        if (page == null) page = 1;
        Seller connected = (Seller) session.getAttribute("connected");
        ListResponse sales = saleService.search(saleFilter, connected.getShop().getId(), page);
        modelAndView.addObject("requiredPages", saleService.getRequiredPages(sales.getCount()));
        modelAndView.addObject("sales",sales);
        modelAndView.addObject("page", page);
        if (saleFilter != null) modelAndView.addObject("saleFilter", saleFilter);
        modelAndView.setViewName("sales/list-sales");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView details(ModelAndView modelAndView, @PathVariable Long id) throws Exception {
        securityManager.isSeller();
        Sale sale = saleService.findById(id);
        ListResponse details = saleDetailsService.search(new Object(), id, null);
        modelAndView.addObject("details",details);
        modelAndView.addObject("sale", sale);
        modelAndView.setViewName("sales/sheet-sale");
        return modelAndView;
    }
}
