package com.eval1.controllers;

import com.eval1.models.laptop.view.LaptopInput;
import com.eval1.models.ram.RamType;
import com.eval1.security.SecurityManager;
import com.eval1.services.LaptopService;
import com.eval1.services.RamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class LaptopController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    LaptopService laptopService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.addObject("laptopForm", laptopService.getLaptopForm());
        modelAndView.setViewName("laptops/create-laptop");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute LaptopInput laptopInput) throws Exception {
        securityManager.isAdmin();
        try {
            laptopService.create(laptopInput.getLaptop());
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
