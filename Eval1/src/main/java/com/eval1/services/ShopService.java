package com.eval1.services;

import com.eval1.repositories.ShopRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Integer;
import java.lang.String;
import com.eval1.models.Shop;


@Service
public class ShopService extends CrudService<Shop, ShopRepo> {

    public ShopService(ShopRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Shop> getEntityClass() {
        return Shop.class;
    }

    
}
