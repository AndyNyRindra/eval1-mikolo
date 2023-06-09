package com.eval1.controllers;

import com.eval1.exception.UnauthorizedException;
import com.eval1.models.seller.Seller;
import com.eval1.security.SecurityManager;
import com.eval1.services.SellerLoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @Autowired
    SellerLoginService sellerLoginService;

    @Autowired
    SecurityManager securityManager;

    @GetMapping({"/logout"})
    public String logout(HttpSession session) throws UnauthorizedException {
        securityManager.isConnected();
        if (session.getAttribute("seller") != null)
            this.sellerLoginService.logout("seller");
        else if (session.getAttribute("admin") != null)
            this.sellerLoginService.logout("admin");
        return "redirect:/login";
    }

}
