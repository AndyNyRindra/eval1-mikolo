package com.eval1.models.cpu;


import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CpuInput {

    private String name;
    private Double frequency;

    public Cpu getCpu() throws CustomException {
        Cpu cpu = new Cpu();
        cpu.setFrequency(frequency);
        cpu.setName(name);
        return cpu;
    }
}
