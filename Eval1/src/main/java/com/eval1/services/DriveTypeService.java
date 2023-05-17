package com.eval1.services;

import com.eval1.models.ram.RamType;
import com.eval1.repositories.DriveTypeRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.drive.DriveType;


@Service
public class DriveTypeService extends CrudService<DriveType, DriveTypeRepo> {

    public DriveTypeService(DriveTypeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<DriveType> getEntityClass() {
        return DriveType.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    boolean isDriveTypeAlreadyExists(DriveType driveType) throws CustomException {
        if (driveType.getId() != null) {
            if (repo.findByNameAndId(driveType.getName(), driveType.getId()).isEmpty()) {
                return isNameUsed(driveType.getName());
            }
            return false;
        }
        return isNameUsed(driveType.getName());
    }

    boolean isNameUsed(String name) throws CustomException {
        if (!repo.findByName(name).isEmpty()) {
            throw new CustomException("Ce nom est déjà utilisé");
        }
        return false;
    }

    @Override
    public DriveType create(DriveType obj) throws Exception {
        if (!isDriveTypeAlreadyExists(obj)) {
            return super.create(obj);
        }
        return null;
    }

    @Override
    public DriveType update(DriveType obj) throws Exception {
        if (!isDriveTypeAlreadyExists(obj)) {
            return super.update(obj);
        }
        return null;
    }

}
