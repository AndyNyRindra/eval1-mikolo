package com.eval1.services;

import com.eval1.models.shop.Shop;
import custom.springutils.service.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import com.eval1.repositories.ShopRepo;
import com.eval1.models.stock.Movement;
import com.eval1.repositories.MovementRepo;


@Service
public class MovementService extends CrudServiceWithFK<Movement, Shop, ShopRepo, MovementRepo> {

    public MovementService(MovementRepo repo, EntityManager manager, ShopRepo refRepo) {
        super(repo, manager, refRepo);
    }

    @Override
    public Object getFKValue(Shop ref) {
        return ref.getId();
    }

    @Override
    public Class<Movement> getEntityClass() {
        return Movement.class;
    }

    @Override
    public String getFkName() {
        return "shopId";
    }
}
