package com.eval1.services;

import com.eval1.authentication.SessionLoginService;
import com.eval1.models.seller.Seller;
import com.eval1.repositories.SellerRepo;
import custom.springutils.AuthenticatedDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;


@Service
public class SellerLoginService extends SessionLoginService<Seller, SellerRepo> {
    public SellerLoginService(SellerRepo repo, HttpSession session) {
        super(repo, "seller", session);
    }

    @Override
    public AuthenticatedDetails<Seller> login(Seller entity) throws Exception {
        Seller e = this.getLoginDetails(entity);
        if (e.isSimpleSeller()) {
            return super.login(entity);
        }

        else {
            super.setSessionName("admin");
            return super.login(entity);
        }

    }

}
