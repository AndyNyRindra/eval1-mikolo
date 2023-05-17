package com.eval1.services;

import com.eval1.models.laptop.LaptopFilter;
import com.eval1.models.laptop.view.LaptopForm;
import com.eval1.repositories.LaptopRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.search.Condition;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.laptop.Laptop;

import java.util.ArrayList;
import java.util.List;


@Service
public class LaptopService extends CrudService<Laptop, LaptopRepo> {

    @Autowired
    private BrandService brandService;

    @Autowired
    private RamTypeService ramTypeService;

    @Autowired
    private DriveTypeService driveTypeService;

    @Autowired
    private CpuService cpuService;

    @Autowired
    private ScreenTypeService screenTypeService;

    public LaptopService(LaptopRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Laptop> getEntityClass() {
        return Laptop.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    boolean isLaptopAlreadyExists(Laptop laptop) throws CustomException {
        if (laptop.getId() != null) {
            if (repo.findByReferenceAndId(laptop.getReference(), laptop.getId()).isEmpty()) {
                return isReferenceUsed(laptop.getReference());
            }
            return false;
        }
        return isReferenceUsed(laptop.getReference());
    }

    boolean isReferenceUsed(String ref) throws CustomException {
        if (!repo.findByReference(ref).isEmpty()) {
            throw new CustomException("Cette référence est déjà utilisée");
        }
        return false;
    }

    @Override
    public Laptop create(Laptop obj) throws Exception {
        if (!isLaptopAlreadyExists(obj)) {
            return super.create(obj);
        }
        return null;
    }

    @Override
    public Laptop update(Laptop obj) throws Exception {
        if (!isLaptopAlreadyExists(obj)) {
            return super.update(obj);
        }
        return null;
    }

    public LaptopForm getLaptopForm() throws Exception {
        LaptopForm laptopForm = new LaptopForm();
        laptopForm.setBrands(brandService.findAll());
        laptopForm.setRamTypes(ramTypeService.findAll());
        laptopForm.setDriveTypes(driveTypeService.findAll());
        laptopForm.setCpus(cpuService.findAll());
        laptopForm.setScreenTypes(screenTypeService.findAll());
        return laptopForm;
    }


    public String getCondition(String name, List<Long> values){
        String condition = "";
        for(int i=0; i < values.size(); i++
        ) {
            if(i != values.size() - 1){
                condition += " u."+name+".id="+values.get(i)+" or ";
            }
            else {
                condition += " u."+name+".id="+values.get(i) + " ";
            }
        }
        return " and ("+condition+") ";
    }

    @Override
    public List<Condition> getAdditionalConditionFrom(Object filter) throws Exception {
        LaptopFilter laptopFilter = (LaptopFilter) filter;
        List<Condition> conditions = new ArrayList<>();

        if (laptopFilter.getBrands() != null) {
            Condition condition = new Condition();
            condition.setCondition(getCondition("brand", laptopFilter.getBrands()));
            conditions.add(condition);
        }
        if(laptopFilter.getScreens() != null){
            Condition condition = new Condition();
            condition.setCondition(getCondition("screenType", laptopFilter.getScreens()));
            conditions.add(condition);
        }
        if(laptopFilter.getRams() != null){
            Condition condition = new Condition();
            condition.setCondition(getCondition("ramType", laptopFilter.getRams()));
            conditions.add(condition);
        }
        if(laptopFilter.getDrives() != null){
            Condition condition = new Condition();
            condition.setCondition(getCondition("driverType", laptopFilter.getDrives()));
            conditions.add(condition);
        }
        if(laptopFilter.getCpus() != null){
            Condition condition = new Condition();
            condition.setCondition(getCondition("cpu", laptopFilter.getCpus()));
            conditions.add(condition);
        }
        return conditions;
    }

}
