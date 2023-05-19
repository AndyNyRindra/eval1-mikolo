package com.eval1.controllers;

import com.eval1.models.comission.Comission;
import com.eval1.models.comission.ComissionFilter;
import com.eval1.models.comission.ComissionInput;
import com.eval1.models.drive.DriveFilter;
import com.eval1.models.drive.DriveType;
import com.eval1.security.SecurityManager;
import com.eval1.services.ComissionService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/comissions")
public class ComissionController {

    @Autowired
    ComissionService comissionService;

    @Autowired
    SecurityManager securityManager;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("comissions/create-comission");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute ComissionInput comissionInput) throws Exception {
        securityManager.isAdmin();
        try {
            comissionService.create(comissionInput.getComission());
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }


    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        Comission comission = comissionService.findById(id);
        modelAndView.addObject("comission", comission);
        modelAndView.setViewName("comissions/update-comission");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute ComissionInput comissionInput, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {
            Comission comission = comissionInput.getComission();
            comission.setId(id);
            comissionService.update(comission);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, ComissionFilter comissionFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse comissions = comissionService.search(comissionFilter, page);
        modelAndView.addObject("requiredPages", comissionService.getRequiredPages(comissions.getCount()));
        modelAndView.addObject("comissions",comissions);
        modelAndView.addObject("page", page);
        if (comissionFilter != null) modelAndView.addObject("comissionFilter", comissionFilter);
        modelAndView.setViewName("comissions/list-comissions");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            comissionService.delete(id);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

}
