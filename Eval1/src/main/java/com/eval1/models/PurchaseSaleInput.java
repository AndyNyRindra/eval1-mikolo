package com.eval1.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
public class PurchaseSaleInput {

    private String reference;

    private Timestamp date;

    private String date1;
    List<Long> laptops;
    List<Double> quantities;

    public void setDate1(String date1) {
        if(date1 != null && !date1.isEmpty()) {
            this.date1 = date1;
            setDate(Timestamp.valueOf(date1 + " 00:00:00"));
        }
    }
}
