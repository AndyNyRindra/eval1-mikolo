package com.eval1.models.stock;

import com.eval1.models.purchase.PurchaseDetails;
import com.eval1.models.receipt.ReceiptDetails;
import com.eval1.models.sale.SaleDetails;
import com.eval1.models.shop.Shop;
import com.eval1.models.transfer.TransferDetails;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasFK;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



import java.sql.Timestamp;
import java.lang.Integer;
import java.lang.Double;


@Getter
@Setter
@Entity
@Table(name = "movement")
public class Movement extends HasFK<Shop> {

	private Timestamp date;
	private Integer movementTypeId;
	private Integer shopId;
	private Double quantity;
	private Integer laptopId;

    public Movement() {

    }


    @Override
    public void setFK(Shop fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("Shop is null");
        }
        setShopId(fk.getId().intValue());
    }

    public Movement (PurchaseDetails purchaseDetails, Integer shopId, Timestamp date) throws CustomException {
        setDate(date);
        setMovementTypeId(0);
        setShopId(shopId);
        setQuantity(purchaseDetails.getQuantity());
        setLaptopId(purchaseDetails.getLaptop().getId().intValue());
    }

    public Movement(TransferDetails transferDetails, Integer shopId, Timestamp date) throws CustomException {
        setDate(date);
        setMovementTypeId(10);
        setShopId(shopId);
        setQuantity(transferDetails.getQuantity());
        setLaptopId(transferDetails.getLaptop().getId().intValue());
    }

    public Movement(ReceiptDetails receiptDetails, Integer shopId, Timestamp date) throws CustomException {
        setDate(date);
        setMovementTypeId(0);
        setShopId(shopId);
        setQuantity(receiptDetails.getQuantity());
        setLaptopId(receiptDetails.getTransferDetails().getLaptop().getId().intValue());
    }

    public Movement(SaleDetails saleDetails, Integer shopId, Timestamp date) throws CustomException {
        setDate(date);
        setMovementTypeId(10);
        setShopId(shopId);
        setQuantity(saleDetails.getQuantity());
        setLaptopId(saleDetails.getLaptop().getId().intValue());
    }

    public void setQuantity(Double quantity) throws CustomException {
        if (quantity == null || quantity <= 0) {
            throw new CustomException("La quantité est nulle ou négative");
        }
        this.quantity = quantity;
    }
}