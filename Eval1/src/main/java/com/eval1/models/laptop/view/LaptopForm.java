package com.eval1.models.laptop.view;

import com.eval1.models.brand.Brand;
import com.eval1.models.cpu.Cpu;
import com.eval1.models.drive.DriveType;
import com.eval1.models.ram.RamType;
import com.eval1.models.screen.ScreenType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaptopForm {

    private Iterable<Cpu> cpus;
    private Iterable<RamType> ramTypes;
    private Iterable<DriveType> driveTypes;
    private Iterable<ScreenType> screenTypes;
    private Iterable<Brand> brands;
}
