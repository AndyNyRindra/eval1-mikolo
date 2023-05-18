package com.eval1.services;

import com.eval1.repositories.SellerRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.seller.Seller;


@Service
public class SellerService extends CrudService<Seller, SellerRepo> {

    public SellerService(SellerRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Seller> getEntityClass() {
        return Seller.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


}
