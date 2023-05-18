package com.eval1.repositories;

import com.eval1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.shop.Shop;

import java.util.List;

public interface ShopRepo extends JpaRepository<Shop, Long> {

    List<Shop> findByName(String name);

    List<Shop> findByNameAndId(String name, Long id);

    List<Shop> findByRole(Role role);
}
