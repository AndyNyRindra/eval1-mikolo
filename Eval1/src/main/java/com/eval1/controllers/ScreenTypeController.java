package com.eval1.controllers;

import com.eval1.models.ram.RamType;
import com.eval1.models.ram.RamTypeFilter;
import com.eval1.models.screen.ScreenType;
import com.eval1.models.screen.ScreenTypeFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.RamTypeService;
import com.eval1.services.ScreenTypeService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/screens")
public class ScreenTypeController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    ScreenTypeService screenTypeService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("screens/create-screen");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute ScreenType screenType) throws Exception {
        securityManager.isAdmin();
        try {
            screenTypeService.create(screenType);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        ScreenType screenType = screenTypeService.findById(id);
        modelAndView.addObject("screenType", screenType);
        modelAndView.setViewName("screens/update-screen");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute ScreenType screenType, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {

            screenType.setId(id);
            screenTypeService.update(screenType);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, ScreenTypeFilter screenTypeFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse screens = screenTypeService.search(screenTypeFilter, page);
        modelAndView.addObject("requiredPages", screenTypeService.getRequiredPages(screens.getCount()));
        modelAndView.addObject("screenTypes",screens);
        modelAndView.addObject("page", page);
        if (screenTypeFilter != null) modelAndView.addObject("screenTypeFilter", screenTypeFilter);
        modelAndView.setViewName("screens/list-screens");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        ModelAndView modelAndView = new ModelAndView();
        screenTypeService.delete(id);
        modelAndView.setViewName("redirect:/screens");
        return modelAndView;
    }
}
