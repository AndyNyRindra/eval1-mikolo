package com.eval1.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
public class PurchaseSaleInput {

    private String reference;
    private Timestamp date;
    List<Long> laptops;
    List<Double> quantities;
}
