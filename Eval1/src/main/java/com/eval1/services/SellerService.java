package com.eval1.services;

import com.eval1.repositories.SellerRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import com.eval1.models.Seller;


@Service
public class SellerService extends CrudService<Seller, SellerRepo> {

    public SellerService(SellerRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Seller> getEntityClass() {
        return Seller.class;
    }

}
