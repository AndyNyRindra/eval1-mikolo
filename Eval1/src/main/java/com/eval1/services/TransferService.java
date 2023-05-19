package com.eval1.services;

import com.eval1.models.laptop.Laptop;
import com.eval1.models.seller.Seller;
import com.eval1.models.shop.Shop;
import com.eval1.models.stock.Movement;
import com.eval1.models.transfer.Transfer;
import com.eval1.models.transfer.TransferDetails;
import com.eval1.repositories.TransferRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.search.Condition;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class TransferService extends CrudService<Transfer, TransferRepo> {

    @Autowired
    private TransferDetailsService transferDetailsService;

    @Autowired
    private MovementService movementService;

    @Autowired
    private VStockService vStockService;

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private HttpSession session;

    public TransferService(TransferRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Transfer> getEntityClass() {
        return Transfer.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    boolean isTransferAlreadyExists(Transfer transfer) throws CustomException {
        if (transfer.getId() != null) {
            if (repo.findByReferenceAndId(transfer.getReference(), transfer.getId()).isEmpty()) {
                return isReferenceUsed(transfer.getReference());
            }
            return false;
        }
        return isReferenceUsed(transfer.getReference());
    }

    boolean isReferenceUsed(String ref) throws CustomException {
        if (!repo.findByReference(ref).isEmpty()) {
            throw new CustomException("Cette référence est déjà utilisée");
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Transfer create(Transfer obj) throws Exception {
        if (!isTransferAlreadyExists(obj)) {
//            obj.setDate(Timestamp.valueOf(LocalDateTime.now()));
            obj.setStatus(0);
            Transfer toSave = super.create(obj);
            for (TransferDetails transferDetails : obj.getTransferDetails()) {
                vStockService.isLaptopQuantityEnough(obj.getShopSender().getId().intValue(), transferDetails.getLaptop().getId().intValue(), transferDetails.getQuantity());
                transferDetails.setTransferId(toSave.getId().intValue());
                Laptop laptop = laptopService.findById(transferDetails.getLaptop().getId());
                transferDetails.setLaptop(laptop);
                transferDetails.setUnitPrice(laptop.getPrice());
                Movement movement = new Movement(transferDetails, obj.getShopSender().getId().intValue(), obj.getDate());
                movementService.create(movement);
                transferDetailsService.create(transferDetails);
            }
            obj.setAmount();
            return super.create(obj);
        }
        return null;
    }

    @Override
    public List<Condition> getAdditionalConditionFrom(Object filter) throws Exception {
        List<Condition> list = super.getAdditionalConditionFrom(filter);
        Seller seller = (Seller) session.getAttribute("connected");
        Shop shop = seller.getShop();
        Condition condition = new Condition();
        condition.setCondition(" and shopReceiver.id =" + shop.getId() + " ");
        list.add(condition);

        Condition condition2 = new Condition();
        condition2.setCondition(" and status = 0 ");
        list.add(condition2);
        return list;
    }
}
