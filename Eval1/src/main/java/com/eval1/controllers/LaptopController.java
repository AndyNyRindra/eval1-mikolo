package com.eval1.controllers;

import com.eval1.models.laptop.Laptop;
import com.eval1.models.laptop.LaptopFilter;
import com.eval1.models.laptop.view.LaptopInput;
import com.eval1.models.ram.RamType;
import com.eval1.models.ram.RamTypeFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.LaptopService;
import com.eval1.services.RamTypeService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/laptops"})
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

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        Laptop laptop = laptopService.findById(id);
        modelAndView.addObject("laptopForm", laptopService.getLaptopForm());
        modelAndView.addObject("laptop", laptop);
        modelAndView.setViewName("laptops/update-laptop");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute LaptopInput laptopInput, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {
            Laptop laptop = laptopInput.getLaptop();
            laptop.setId(id);
            laptopService.update(laptop);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, LaptopFilter laptopFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse laptops = laptopService.search(laptopFilter, page);
        modelAndView.addObject("requiredPages", laptopService.getRequiredPages(laptops.getCount()));
        modelAndView.addObject("laptops",laptops);
        modelAndView.addObject("laptopForm", laptopService.getLaptopForm());
        modelAndView.addObject("page", page);
        if (laptopFilter != null) modelAndView.addObject("laptopFilter", laptopFilter);
        modelAndView.setViewName("laptops/list-laptops");
        return modelAndView;
    }
}
