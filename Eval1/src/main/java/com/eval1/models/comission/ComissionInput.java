package com.eval1.models.comission;

import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComissionInput {

    private Double minValue;
    private Double percent;
    private Double maxValue;

    public Comission getComission() throws CustomException {
        Comission comission = new Comission();
        comission.setMinValue(getMinValue());
        comission.setPercent(getPercent());
        comission.setMaxValue(getMaxValue());
        return comission;
    }

}
