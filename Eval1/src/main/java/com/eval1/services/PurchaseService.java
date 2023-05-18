package com.eval1.services;

import com.eval1.models.laptop.Laptop;
import com.eval1.models.stock.Movement;
import com.eval1.models.purchase.PurchaseDetails;
import com.eval1.models.shop.Shop;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.String;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.eval1.repositories.ShopRepo;
import com.eval1.models.purchase.Purchase;
import com.eval1.repositories.PurchaseRepo;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PurchaseService extends CrudServiceWithFK<Purchase, Shop, ShopRepo, PurchaseRepo> {

    @Autowired
    private PurchaseDetailsService purchaseDetailsService;

    @Autowired
    private MovementService movementService;

    @Autowired
    private LaptopService laptopService;

    @Autowired

    public PurchaseService(PurchaseRepo repo, EntityManager manager, ShopRepo refRepo) {
        super(repo, manager, refRepo);
    }

    @Override
    public Object getFKValue(Shop ref) {
        return ref;
    }

    @Override
    public Class<Purchase> getEntityClass() {
        return Purchase.class;
    }

    @Override
    public String getFkName() {
        return "shop";
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    boolean isPurchaseAlreadyExists(Purchase purchase) throws CustomException {
        if (purchase.getId() != null) {
            if (repo.findByReferenceAndId(purchase.getReference(), purchase.getId()).isEmpty()) {
                return isReferenceUsed(purchase.getReference());
            }
            return false;
        }
        return isReferenceUsed(purchase.getReference());
    }

    boolean isReferenceUsed(String ref) throws CustomException {
        if (!repo.findByReference(ref).isEmpty()) {
            throw new CustomException("Cette référence est déjà utilisée");
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Purchase create(Purchase obj) throws Exception {
        if (!isPurchaseAlreadyExists(obj)) {
            obj.setDate(Timestamp.valueOf(LocalDateTime.now()));
            Purchase toSave = super.create(obj);
            for (PurchaseDetails purchaseDetails : obj.getPurchaseDetails()) {
                purchaseDetails.setPurchaseId(toSave.getId().intValue());
                Laptop laptop = laptopService.findById(purchaseDetails.getLaptop().getId());
                purchaseDetails.setLaptop(laptop);
                purchaseDetails.setAmount();
                Movement movement = new Movement(purchaseDetails, obj.getShop().getId().intValue(), obj.getDate());
                movementService.create(movement);
                purchaseDetailsService.create(purchaseDetails);
            }
            obj.setAmount();
            return super.create(obj);

        }
        return null;
    }
}
