package com.eval1.models.shop;

import com.eval1.models.Role;
import custom.springutils.search.FilterObject;
import custom.springutils.search.map.Filter;
import custom.springutils.search.map.IgnoreMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopFilter {

    @Filter("ilike_name")
    private String name;

    @IgnoreMapping
    private Integer roleId;

    private Role role;

    public void setName(String name) {
        this.name = "%" + name + "%" ;
    }

    public void setRoleId(Integer roleId) {
        if (roleId != null) {
            this.roleId = roleId;
            Role role1 = new Role();
            role1.setId(Long.valueOf(roleId));
            setRole(role1);
        }
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("name");
        if (getName() != null) {
            filterConditions.append("=" +getName());
        }
        filterConditions.append("&roleId");
        if (getRoleId() != null) {
            filterConditions.append("=" +getRoleId());
        }

        return filterConditions.toString().replace("%", "");
    }
}
