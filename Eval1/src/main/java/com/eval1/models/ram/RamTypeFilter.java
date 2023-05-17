package com.eval1.models.ram;

import custom.springutils.search.map.Filter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RamTypeFilter {

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
