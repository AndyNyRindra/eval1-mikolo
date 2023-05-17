package com.eval1.models.brand;

import com.eval1.models.Role;
import custom.springutils.search.FilterObject;
import custom.springutils.search.map.Filter;
import custom.springutils.search.map.IgnoreMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandFilter {

    @Filter("ilike_name")
    private String name;

    public void setName(String name) {
        this.name = "%" + name + "%" ;
    }



    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("name");
        if (getName() != null) {
            filterConditions.append("=" +getName());
        }

        return filterConditions.toString().replace("%", "");
    }
}
