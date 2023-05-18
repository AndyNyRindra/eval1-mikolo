package com.eval1.services;

import com.eval1.authentication.SessionLoginService;
import com.eval1.models.seller.Seller;
import com.eval1.repositories.SellerRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService extends SessionLoginService<Seller, SellerRepo> {
    public AdminLoginService(SellerRepo repo, HttpSession session) {
        super(repo, "admin", session);
    }
}
