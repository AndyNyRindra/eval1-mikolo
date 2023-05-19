package com.eval1.repositories;

import com.eval1.models.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.transfer.Transfer;

import java.util.List;

public interface TransferRepo extends JpaRepository<Transfer, Long> {
    List<Transfer> findByReference(String reference);

    List<Transfer> findByReferenceAndId(String reference, Long id);


    List<Transfer> findByStatusAndShopReceiver(Integer status, Shop shopReceiver);
}
