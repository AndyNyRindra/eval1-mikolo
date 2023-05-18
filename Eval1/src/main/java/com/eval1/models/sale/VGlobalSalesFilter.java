package com.eval1.models.sale;

import custom.springutils.search.FilterObject;
import custom.springutils.search.map.Filter;

import java.util.Date;

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

}
