package com.eval1.controllers;

import com.eval1.models.ram.RamType;
import com.eval1.models.brand.BrandFilter;
import com.eval1.models.ram.RamTypeFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.RamTypeService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rams")
public class RamTypeController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    RamTypeService ramTypeService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("rams/create-ram");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute RamType ramType) throws Exception {
        securityManager.isAdmin();
        try {
            ramTypeService.create(ramType);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        RamType ramType = ramTypeService.findById(id);
        modelAndView.addObject("ramType", ramType);
        modelAndView.setViewName("rams/update-ram");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute RamType ramType, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            ramType.setId(id);
            ramTypeService.update(ramType);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, RamTypeFilter ramTypeFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse rams = ramTypeService.search(ramTypeFilter, page);
        modelAndView.addObject("requiredPages", ramTypeService.getRequiredPages(rams.getCount()));
        modelAndView.addObject("ramTypes",rams);
        modelAndView.addObject("page", page);
        if (ramTypeFilter != null) modelAndView.addObject("ramTypeFilter", ramTypeFilter);
        modelAndView.setViewName("rams/list-rams");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            ramTypeService.delete(id);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
