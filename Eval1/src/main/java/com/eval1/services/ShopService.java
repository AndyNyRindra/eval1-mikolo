package com.eval1.services;

import com.eval1.repositories.ShopRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.lang.String;
import com.eval1.models.shop.Shop;


@Service
public class ShopService extends CrudService<Shop, ShopRepo> {

    public ShopService(ShopRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Shop> getEntityClass() {
        return Shop.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    boolean isShopAlreadyExists(Shop shop) throws CustomException {
        if (shop.getId() != null) {
            if (repo.findByNameAndId(shop.getName(), shop.getId()).isEmpty()) {
                return isNameUsed(shop.getName());
            }
            return false;
        }
        return isNameUsed(shop.getName());
    }

    boolean isNameUsed(String name) throws CustomException {
        if (!repo.findByName(name).isEmpty()) {
            throw new CustomException("Ce nom est déjà utilisé");
        }
        return false;
    }

    @Override
    public Shop create(Shop obj) throws Exception {
        if (!isShopAlreadyExists(obj)) {
            return super.create(obj);
        }
        return null;
    }

    @Override
    public Shop update(Shop obj) throws Exception {
        if (!isShopAlreadyExists(obj)) {
            return super.update(obj);
        }
        return null;
    }
}
