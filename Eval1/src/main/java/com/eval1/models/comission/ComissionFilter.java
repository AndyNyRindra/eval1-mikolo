package com.eval1.models.comission;

import custom.springutils.search.map.Filter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComissionFilter {

    @Filter("mineq_minValue")
    private Double minValue;

    @Filter("mineq_percent")
    private Double minPercent;

    @Filter("mineq_percent")
    private Double maxPercent;

    @Filter("mineq_maxValue")
    private Double maxValue;

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("minValue");
        if (getMinValue() != null) {
            filterConditions.append("=" +getMinValue());
        }
        filterConditions.append("&maxValue");
        if (getMaxValue() != null) {
            filterConditions.append("=" +getMaxValue());
        }
        filterConditions.append("&minPercent");
        if (getMinPercent() != null) {
            filterConditions.append("=" +getMinPercent());
        }
        filterConditions.append("&maxPercent");
        if (getMaxPercent() != null) {
            filterConditions.append("=" +getMaxPercent());
        }
        return filterConditions.toString().replace("%", "");
    }
}
