package com.eval1.models.sale;

import custom.springutils.search.FilterObject;
import custom.springutils.search.map.Filter;
import custom.springutils.search.map.IgnoreMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class VGlobalSalesFilter extends FilterObject {

    @Filter("mineq_mois")
    private Date minDate;

    @Filter("maxeq_mois")
    private Date maxDate;


    @IgnoreMapping
    private Integer year;

    public void setYear(Integer year) {
        if(year != null) {
            this.year = year;
            setMinDate(new Date(year-1900, 0, 1));
            setMaxDate(new Date(year-1900, 11, 31));
        }
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("year");
        if (getYear() != null) {
            filterConditions.append("=" +getYear());
        }
        return filterConditions.toString().replace("%", "");
    }

}
