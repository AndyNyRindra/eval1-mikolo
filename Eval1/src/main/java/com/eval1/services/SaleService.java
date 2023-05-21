package com.eval1.services;

import com.eval1.models.comission.Comission;
import com.eval1.models.laptop.Laptop;
import com.eval1.models.purchase.Purchase;
import com.eval1.models.purchase.PurchaseDetails;
import com.eval1.models.sale.SaleDetails;
import com.eval1.models.shop.Shop;
import com.eval1.models.stock.Movement;
import com.eval1.models.stock.VStock;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.String;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.eval1.repositories.ShopRepo;
import com.eval1.models.sale.Sale;
import com.eval1.repositories.SaleRepo;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SaleService extends CrudServiceWithFK<Sale, Shop, ShopRepo, SaleRepo> {

    @Autowired
    private SaleDetailsService saleDetailsService;


    @Autowired
    private MovementService movementService;

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private VStockService vStockService;

    public SaleService(SaleRepo repo, EntityManager manager, ShopRepo refRepo) {
        super(repo, manager, refRepo);
    }

    @Override
    public Object getFKValue(Shop ref) {
        return ref;
    }

    @Override
    public Class<Sale> getEntityClass() {
        return Sale.class;
    }

    @Override
    public String getFkName() {
        return "shop";
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    boolean isSaleAlreadyExists(Sale sale) throws CustomException {
        if (sale.getId() != null) {
            if (repo.findByReferenceAndId(sale.getReference(), sale.getId()).isEmpty()) {
                return isReferenceUsed(sale.getReference());
            }
            return false;
        }
        return isReferenceUsed(sale.getReference());
    }

    boolean isReferenceUsed(String ref) throws CustomException {
        if (!repo.findByReference(ref).isEmpty()) {
            throw new CustomException("Cette référence est déjà utilisée");
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Sale create(Sale obj) throws Exception {
        if (!isSaleAlreadyExists(obj)) {
            obj.setAmount(1.0);
//            obj.setDate(Timestamp.valueOf(LocalDateTime.now()));
            Sale toSave = super.create(obj);
            for (SaleDetails saleDetails : obj.getSaleDetails()) {
                vStockService.isLaptopQuantityEnough(obj.getShop().getId().intValue(), saleDetails.getLaptop().getId().intValue(), saleDetails.getQuantity());
                saleDetails.setSaleId(toSave.getId().intValue());
                Laptop laptop = laptopService.findById(saleDetails.getLaptop().getId());
                saleDetails.setLaptop(laptop);
                saleDetails.setUnitPrice(laptop.getSellingPrice());
                Movement movement = new Movement(saleDetails, obj.getShop().getId().intValue(), obj.getDate());
                movementService.create(movement);
                saleDetailsService.create(saleDetails);
            }
            obj.setAmount();
            return super.create(obj);
        }
        return null;
    }

}
