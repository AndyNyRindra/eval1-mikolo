package com.eval1.services;

import com.eval1.models.laptop.view.LaptopForm;
import com.eval1.repositories.LaptopRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.laptop.Laptop;


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

}
