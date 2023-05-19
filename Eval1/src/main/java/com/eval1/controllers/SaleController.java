package com.eval1.controllers;

import com.eval1.models.ActionType;
import com.eval1.models.drive.DriveFilter;
import com.eval1.models.purchase.Purchase;
import com.eval1.models.purchase.PurchaseInput;
import com.eval1.models.sale.*;
import com.eval1.models.seller.Seller;
import com.eval1.models.shop.Shop;
import com.eval1.security.SecurityManager;
import com.eval1.services.*;
import custom.springutils.controller.CrudWithFK;
import custom.springutils.util.ListResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private VGlobalSalesService vGlobalSalesService;

    @Autowired
    private VShopSalesService vShopSalesService;

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


    @GetMapping("/create")
    public ModelAndView create(ModelAndView model) throws Exception {
        securityManager.isSeller();
        model.addObject("laptops",laptopService.findAvailableByShopId( ((Seller) session.getAttribute("connected")).getShop().getId()));
        model.addObject("actionType", new ActionType("Vente","sales"));
        model.setViewName("purchases/create-purchase");
        return model;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute SaleInput saleInput) throws Exception {
        securityManager.isSeller();
        try {
            Sale sale = saleInput.getSale();
            Seller seller = (Seller) session.getAttribute("connected");
            Shop shop = seller.getShop();
            sale.setFK(shop);
            saleService.create(sale);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/stats/global")
    public ModelAndView globalStats(ModelAndView modelAndView, VGlobalSalesFilter saleFilter, @RequestParam(name = "page", required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;

        ListResponse stats = vGlobalSalesService.search(saleFilter, page);
        if (saleFilter != null) modelAndView.addObject("saleFilter", saleFilter);
        modelAndView.addObject("stats", stats);
        modelAndView.addObject("requiredPages", vGlobalSalesService.getRequiredPages(stats.getCount()));
        modelAndView.addObject("page", page);
        modelAndView.setViewName("sales/global-stats-table");
        return modelAndView;
    }

    @GetMapping("/stats/shops")
    public ModelAndView shopStats(ModelAndView modelAndView, VGlobalSalesFilter saleFilter, @RequestParam(name = "page", required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;

        ListResponse stats = vShopSalesService.search(saleFilter, page);
        if (saleFilter != null) modelAndView.addObject("saleFilter", saleFilter);
        modelAndView.addObject("stats", stats);
        modelAndView.addObject("requiredPages", vShopSalesService.getRequiredPages(stats.getCount()));
        modelAndView.addObject("page", page);
        modelAndView.setViewName("sales/shops-stats-table");
        return modelAndView;
    }


    @GetMapping({"stats/global/pdf"})
    public ResponseEntity<?> createPDF() throws Exception {
        securityManager.isAdmin();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "vente-par-mois.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity(vGlobalSalesService.createPDF(), headers, HttpStatus.OK);
    }

    @GetMapping({"stats/shops/pdf"})
    public ResponseEntity<?> createShopPDF() throws Exception {
        securityManager.isAdmin();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "vente-par-mois-par-point-vente.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity(vShopSalesService.createPDF(), headers, HttpStatus.OK);
    }
}
