package com.eval1.services;

import com.eval1.repositories.VStockRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Integer;
import java.lang.Double;
import java.lang.Long;
import com.eval1.models.VStock;


@Service
public class VStockService extends CrudService<VStock, VStockRepo> {

    public VStockService(VStockRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VStock> getEntityClass() {
        return VStock.class;
    }

    public VStock findByShopIdAndLaptopId(Integer shopId, Integer laptopId) {
        return repo.findByShopIdAndLaptopId(shopId, laptopId);
    }

    public boolean isLaptopQuantityEnough(Integer shopId, Integer laptopId, Double quantity) throws CustomException {
        VStock vStock = findByShopIdAndLaptopId(shopId, laptopId);
        if (vStock != null) {
            if (vStock.getQuantity() < quantity)
                throw new CustomException("La quantité demandée est supérieure à la quantité en stock");
        }
        return true;
    }

}
