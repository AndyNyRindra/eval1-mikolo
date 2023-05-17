package com.eval1.models.laptop;

import custom.springutils.search.FilterObject;

public class LaptopFilter extends FilterObject {

    public String getFilterConditions() {
        return "WHERE 1=1";
    }
}
