package com.eval1.controllers;

import com.eval1.models.brand.Brand;
import com.eval1.models.brand.BrandFilter;
import com.eval1.models.shop.ShopFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.BrandService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    BrandService brandService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("brands/create-brand");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute Brand brand) throws Exception {
        securityManager.isAdmin();
        try {
            brandService.create(brand);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        Brand brand = brandService.findById(id);
        modelAndView.addObject("brand", brand);
        modelAndView.setViewName("brands/update-brand");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute Brand brand, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            brand.setId(id);
            brandService.update(brand);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, BrandFilter brandFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse brands = brandService.search(brandFilter, page);
        modelAndView.addObject("requiredPages", brandService.getRequiredPages(brands.getCount()));
        modelAndView.addObject("brands",brands);
        modelAndView.addObject("page", page);
        if (brandFilter != null) modelAndView.addObject("brandFilter", brandFilter);
        modelAndView.setViewName("brands/list-brands");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            brandService.delete(id);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
