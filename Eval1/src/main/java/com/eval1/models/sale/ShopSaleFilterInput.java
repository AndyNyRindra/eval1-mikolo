package com.eval1.models.sale;

import java.util.HashMap;

public class ShopSaleFilterInput {

    public HashMap<Integer, String> getMonths() {
        HashMap<Integer, String> months = new HashMap<Integer, String>();
        months.put(1, "Janvier");
        months.put(2, "Février");
        months.put(3, "Mars");
        months.put(4, "Avril");
        months.put(5, "Mai");
        months.put(6, "Juin");
        months.put(7, "Juillet");
        months.put(8, "Août");
        months.put(9, "Septembre");
        months.put(10, "Octobre");
        months.put(11, "Novembre");
        months.put(12, "Décembre");
        return months;
    }
}
