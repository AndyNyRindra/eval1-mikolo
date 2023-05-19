package com.eval1.controllers;

import com.eval1.models.sale.VGlobalSalesFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.VBeneficeMonthService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BenefController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    VBeneficeMonthService vBeneficeMonthService;

    @GetMapping("/stats/benefs")
    public ModelAndView shopStats(ModelAndView modelAndView, VGlobalSalesFilter saleFilter, @RequestParam(name = "page", required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;

        ListResponse stats = vBeneficeMonthService.search(saleFilter, page);
        if (saleFilter != null) modelAndView.addObject("saleFilter", saleFilter);
        modelAndView.addObject("stats", stats);
        modelAndView.addObject("requiredPages", vBeneficeMonthService.getRequiredPages(stats.getCount()));
        modelAndView.addObject("page", page);
        modelAndView.setViewName("benefs/benefs-stats-table");
        return modelAndView;
    }


    @GetMapping({"stats/benefs/pdf"})
    public ResponseEntity<?> createShopPDF() throws Exception {
        securityManager.isAdmin();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "benefice-par-mois.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity(vBeneficeMonthService.createPDF(), headers, HttpStatus.OK);
    }

}
