package com.eval1.models.sale;

import custom.springutils.search.map.Filter;
import custom.springutils.search.map.IgnoreMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VShopSalesFilter {

    @IgnoreMapping
    private Integer year;

    @IgnoreMapping
    private Integer month;

    @Filter("mineq_mois")
    private Date minDate;

    @Filter("maxeq_mois")
    private Date maxDate;

    public void setConditions() {
        if(year != null && month != null) {
            setMinDate(new Date(year-1900, month-1, 1));
            setMaxDate(new Date(year-1900, month-1, 31));
        }
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("month");
        if (getMonth() != null) {
            filterConditions.append("=" +getMonth());
        }
        filterConditions.append("&year");
        if (getYear() != null) {
            filterConditions.append("=" +getYear());
        }
        return filterConditions.toString().replace("%", "");
    }
}
