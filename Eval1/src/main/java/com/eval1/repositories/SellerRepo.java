package com.eval1.repositories;

import custom.springutils.LoginRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.seller.Seller;

public interface SellerRepo extends JpaRepository<Seller, Long>, LoginRepo<Seller> {
}
