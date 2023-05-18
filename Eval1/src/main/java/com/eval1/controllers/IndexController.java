package com.eval1.controllers;

import com.eval1.exception.UnauthorizedException;
import com.eval1.security.SecurityManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public String index() throws UnauthorizedException {
        securityManager.isConnected();
        if (session.getAttribute("admin") != null)
            return "index";
        else if (session.getAttribute("seller") != null)
            return "redirect:/sales";
        return null;
    }
}
