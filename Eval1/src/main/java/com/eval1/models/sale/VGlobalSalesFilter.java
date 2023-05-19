package com.eval1.models.sale;

import custom.springutils.search.FilterObject;
import custom.springutils.search.map.Filter;
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

    @Filter("mineq_recettes")
    private Double minRecettes;

    @Filter("maxeq_recettes")
    private Double maxRecettes;

    @Filter("mineq_nombreVentes")
    private Long minNombreVentes;

    @Filter("maxeq_nombreVentes")
    private Long maxNombreVentes;


    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("minDate");
        if (getMinDate() != null) {
            filterConditions.append("=" +getMinDate());
        }
        filterConditions.append("&maxDate");
        if (getMaxDate() != null) {
            filterConditions.append("=" +getMaxDate());
        }
        filterConditions.append("&minRecettes");
        if (getMinRecettes() != null) {
            filterConditions.append("=" +getMinRecettes());
        }
        filterConditions.append("&maxRecettes");
        if (getMaxRecettes() != null) {
            filterConditions.append("=" +getMaxRecettes());
        }
        filterConditions.append("&minNombreVentes");
        if (getMinNombreVentes() != null) {
            filterConditions.append("=" +getMinNombreVentes());
        }
        filterConditions.append("&maxNombreVentes");
        if (getMaxNombreVentes() != null) {
            filterConditions.append("=" +getMaxNombreVentes());
        }
        return filterConditions.toString().replace("%", "");
    }

}
