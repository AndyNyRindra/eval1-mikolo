package com.eval1.controllers;

import com.eval1.models.shop.Shop;
import com.eval1.models.shop.ShopFilter;
import com.eval1.models.shop.view.ShopInput;
import com.eval1.security.SecurityManager;
import com.eval1.services.RoleService;
import com.eval1.services.ShopService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    SecurityManager securityManager;


    @Autowired
    RoleService roleService;


    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.addObject("roles",roleService.findAll());
        modelAndView.setViewName("shops/create-shop");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute ShopInput shopInput) throws Exception {
        securityManager.isAdmin();
        try {
            shopService.create(shopInput.getShop());
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        Shop shop = shopService.findById(id);
        modelAndView.addObject("shop", shop);
        modelAndView.addObject("roles",roleService.findAll());
        modelAndView.setViewName("shops/update-shop");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute ShopInput shopInput, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {
            Shop shop = shopInput.getShop();
            shop.setId(id);
            shopService.update(shop);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }


    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, ShopFilter shopFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse shops = shopService.search(shopFilter, page);
        modelAndView.addObject("requiredPages", shopService.getRequiredPages(shops.getCount()));
        modelAndView.addObject("shops",shops);
        modelAndView.addObject("roles",roleService.findAll());
        modelAndView.addObject("page", page);
        if (shopFilter != null) modelAndView.addObject("shopFilter", shopFilter);
        modelAndView.setViewName("shops/list-shops");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        ModelAndView modelAndView = new ModelAndView();
        shopService.delete(id);
        modelAndView.setViewName("redirect:/shops");
        return modelAndView;
    }
}
