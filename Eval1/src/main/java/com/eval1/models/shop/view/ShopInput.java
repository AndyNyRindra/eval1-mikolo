package com.eval1.models.shop.view;

import com.eval1.models.shop.Shop;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopInput {

    private String name;
    private Integer roleId;

    public Shop getShop() {
        Shop shop = new Shop(getRoleId());
        shop.setName(getName());
        return shop;
    }
}
