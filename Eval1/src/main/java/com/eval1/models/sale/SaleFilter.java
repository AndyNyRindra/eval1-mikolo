package com.eval1.models.sale;

import custom.springutils.search.map.Filter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleFilter {

    @Filter("ilike_reference")
    private String reference;

    @Filter("mineq_amount")
    private Double amountMin;

    @Filter("maxeq_amount")
    private Double amountMax;

    public void setReference(String reference) {
        this.reference = "%" + reference + "%";
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("reference");
        if (getReference() != null) {
            filterConditions.append("=" +getReference());
        }
        filterConditions.append("&amountMin");
        if (getAmountMin() != null) {
            filterConditions.append("=" +getAmountMin());
        }
        filterConditions.append("&amountMax");
        if (getAmountMax() != null) {
            filterConditions.append("=" +getAmountMax());
        }
        return filterConditions.toString().replace("%", "");
    }
}
