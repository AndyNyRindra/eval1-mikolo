package com.eval1.models.laptop.view;


import com.eval1.models.laptop.Laptop;
import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaptopInput {

    private String name;
    private Long cpuId;
    private Double ramValue;
    private Long ramTypeId;
    private Double screenSize;
    private Long screenTypeId;
    private Double driverSize;
    private Long driverTypeId;
    private Long brandId;
    private String reference;
    private Double price;
    private Double sellingPrice;

    public Laptop getLaptop() throws CustomException {
        Laptop laptop = new Laptop( cpuId,  ramTypeId,  driverTypeId,  screenTypeId,  brandId);
        laptop.setName(name);
        laptop.setRamValue(ramValue);
        laptop.setScreenSize(screenSize);
        laptop.setDriverSize(driverSize);
        laptop.setReference(reference);
        laptop.setPrice(price);
        laptop.setSellingPrice(sellingPrice);
        return laptop;
    }
}
