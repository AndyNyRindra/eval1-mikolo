package com.eval1.models.laptop.view;

import com.eval1.models.brand.Brand;
import com.eval1.models.cpu.Cpu;
import com.eval1.models.drive.DriveType;
import com.eval1.models.ram.RamType;
import com.eval1.models.screen.ScreenType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class LaptopForm {

    private Iterable<Cpu> cpus;
    private Iterable<RamType> ramTypes;
    private Iterable<DriveType> driveTypes;
    private Iterable<ScreenType> screenTypes;
    private Iterable<Brand> brands;

    public HashMap<String, String> getFields() {
        HashMap<String, String> fields = new HashMap<>();
        fields.put("ramValue", "Taille ram");
        fields.put("driverSize", "Taille disque");
        fields.put("screenSize", "Taille écran");
        fields.put("cpu.frequency", "Fréquence processeur");
        return fields;
    }

    public HashMap<String, String> getMethods() {
        HashMap<String, String> methods = new HashMap<>();
        methods.put("ASC", "Croissant");
        methods.put("DESC", "Décroissant");
        return methods;
    }
}
