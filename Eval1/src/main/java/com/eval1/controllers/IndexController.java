package com.eval1.controllers;

import com.eval1.exception.UnauthorizedException;
import com.eval1.security.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private SecurityManager securityManager;

    @GetMapping("/")
    public String index() throws UnauthorizedException {
        securityManager.isConnected();
        return "index";
    }
}
