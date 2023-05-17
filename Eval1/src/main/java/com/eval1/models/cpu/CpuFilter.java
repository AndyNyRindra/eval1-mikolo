package com.eval1.models.cpu;


import custom.springutils.search.map.Filter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CpuFilter {

    @Filter("ilike_name")
    private String name;

    @Filter("mineq_frequency")
    Double minFrequency;

    @Filter("maxeq_frequency")
    Double maxFrequency;

    public void setName(String name) {
        this.name = "%" + name + "%" ;
    }



    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("name");
        if (getName() != null) {
            filterConditions.append("=" +getName());
        }
        filterConditions.append("minFrequency");
        if (getMinFrequency() != null) {
            filterConditions.append("=" +getMinFrequency());
        }
        filterConditions.append("maxFrequency");
        if (getMaxFrequency() != null) {
            filterConditions.append("=" +getMaxFrequency());
        }
        return filterConditions.toString().replace("%", "");
    }
}
