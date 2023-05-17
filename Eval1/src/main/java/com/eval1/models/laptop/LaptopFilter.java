package com.eval1.models.laptop;

import custom.springutils.search.FilterObject;
import custom.springutils.search.map.Filter;
import custom.springutils.search.map.IgnoreMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LaptopFilter extends FilterObject {

    @Filter("mineq_ramValue")
    private Double minRam;
    @Filter("maxeq_ramValue")
    private Double maxRam;
    @Filter("mineq_driverSize")
    private Double minDrive;
    @Filter("maxeq_driverSize")
    private Double maxDrive;
    @Filter("mineq_screenSize")
    private Double minScreen;
    @Filter("maxeq_screenSize")
    private Double maxScreen;

    @Filter("mineq_cpu.frequency")
    private Double minCpu;

    @Filter("maxeq_cpu.frequency")
    private Double maxCpu;

    @IgnoreMapping
    private String keyWord;

    @IgnoreMapping
    List<Long> brands;

    @IgnoreMapping
    List<Long> rams;

    @IgnoreMapping
    List<Long> drives;

    @IgnoreMapping
    List<Long> cpus;

    @IgnoreMapping
    List<Long> screens;

    public StringBuilder appendConditions(StringBuilder stringBuilder, String name, List<Long> values){
        for (Long l : values
        ) {
            stringBuilder.append("&").append(name).append("=").append(l);
        }
        return stringBuilder;
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("keyWord");
        if (getKeyWord() != null) {
            filterConditions.append("=" + getKeyWord());
        }
        if (brands != null && !getBrands().isEmpty()) {
            filterConditions = appendConditions(filterConditions, "brands", brands);
        }
        if(screens != null && !getScreens().isEmpty()){
            filterConditions = appendConditions(filterConditions, "screens", screens);
        }
        if(rams != null && !getRams().isEmpty()){
            filterConditions = appendConditions(filterConditions, "rams", rams);
        }
        if(drives != null && !getDrives().isEmpty()){
            filterConditions = appendConditions(filterConditions, "drives", drives);
        }
        if(cpus != null && !getCpus().isEmpty()){
            filterConditions = appendConditions(filterConditions, "cpus", cpus);
        }
        filterConditions.append("&minRam");
        if (getMinRam() != null) {
            filterConditions.append("=" + getMinRam());
        }
        filterConditions.append("&maxRam");
        if (getMaxRam() != null) {
            filterConditions.append("=" + getMaxRam());
        }
        filterConditions.append("&minDrive");
        if (getMinDrive() != null) {
            filterConditions.append("=" + getMinDrive());
        }
        filterConditions.append("&maxDrive");
        if (getMaxDrive() != null) {
            filterConditions.append("=" + getMaxDrive());
        }
        filterConditions.append("&minScreen");
        if (getMinScreen() != null) {
            filterConditions.append("=" + getMinScreen());
        }
        filterConditions.append("&maxScreen");
        if (getMaxScreen() != null) {
            filterConditions.append("=" + getMaxScreen());
        }
        filterConditions.append("&minCpu");
        if (getMinCpu() != null) {
            filterConditions.append("=" + getMinCpu());
        }
        filterConditions.append("&maxCpu");
        if (getMaxCpu() != null) {
            filterConditions.append("=" + getMaxCpu());
        }
        return filterConditions.toString().replace("%", "");
    }
}
