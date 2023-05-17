package com.eval1.controllers;

import com.eval1.models.drive.DriveFilter;
import com.eval1.models.drive.DriveType;
import com.eval1.models.ram.RamType;
import com.eval1.models.ram.RamTypeFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.DriveTypeService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/drives")
public class DriveTypeController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    DriveTypeService driveTypeService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("drives/create-drive");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute DriveType driveType) throws Exception {
        securityManager.isAdmin();
        try {
            driveTypeService.create(driveType);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        DriveType driveType = driveTypeService.findById(id);
        modelAndView.addObject("driveType", driveType);
        modelAndView.setViewName("drives/update-drive");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute DriveType driveType, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            driveType.setId(id);
            driveTypeService.update(driveType);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, DriveFilter driveFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse drives = driveTypeService.search(driveFilter, page);
        modelAndView.addObject("requiredPages", driveTypeService.getRequiredPages(drives.getCount()));
        modelAndView.addObject("driveTypes",drives);
        modelAndView.addObject("page", page);
        if (driveFilter != null) modelAndView.addObject("driveFilter", driveFilter);
        modelAndView.setViewName("drives/list-drives");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            driveTypeService.delete(id);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
