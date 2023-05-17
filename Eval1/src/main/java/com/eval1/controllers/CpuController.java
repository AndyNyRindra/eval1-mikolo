package com.eval1.controllers;


import com.eval1.models.cpu.Cpu;
import com.eval1.models.cpu.CpuFilter;
import com.eval1.models.cpu.CpuInput;
import com.eval1.models.ram.RamType;
import com.eval1.models.ram.RamTypeFilter;
import com.eval1.security.SecurityManager;
import com.eval1.services.CpuService;
import custom.springutils.util.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cpus")
public class CpuController {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    CpuService cpuService;

    @GetMapping("/create")
    public ModelAndView loadCreateForm(ModelAndView modelAndView) throws Exception {
        securityManager.isAdmin();
        modelAndView.setViewName("cpus/create-cpu");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute CpuInput cpuInput) throws Exception {
        securityManager.isAdmin();
        try {
            cpuService.create(cpuInput.getCpu());
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView loadUpdateForm(ModelAndView modelAndView, @PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        Cpu cpu = cpuService.findById(id);
        modelAndView.addObject("cpu", cpu);
        modelAndView.setViewName("cpus/update-cpu");
        return modelAndView;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> update(@ModelAttribute CpuInput cpuInput, @PathVariable Long id) throws Exception {
        securityManager.isAdmin();
        try {
            Cpu cpu = cpuInput.getCpu();
            cpu.setId(id);
            cpuService.update(cpu);
            return ResponseEntity.ok("success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @GetMapping
    public ModelAndView list(ModelAndView modelAndView, CpuFilter cpuFilter, @RequestParam(required = false) Integer page) throws Exception {
        securityManager.isAdmin();
        if (page == null) page = 1;
        ListResponse cpus = cpuService.search(cpuFilter, page);
        modelAndView.addObject("requiredPages", cpuService.getRequiredPages(cpus.getCount()));
        modelAndView.addObject("cpus",cpus);
        modelAndView.addObject("page", page);
        if (cpuFilter != null) modelAndView.addObject("cpuFilter", cpuFilter);
        modelAndView.setViewName("cpus/list-cpus");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) throws Exception {
        securityManager.isAdmin();
        ModelAndView modelAndView = new ModelAndView();
        cpuService.delete(id);
        modelAndView.setViewName("redirect:/cpus");
        return modelAndView;
    }
}
