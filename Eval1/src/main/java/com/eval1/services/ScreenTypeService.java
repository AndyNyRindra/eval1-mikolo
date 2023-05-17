package com.eval1.services;

import com.eval1.repositories.ScreenTypeRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.screen.ScreenType;


@Service
public class ScreenTypeService extends CrudService<ScreenType, ScreenTypeRepo> {

    public ScreenTypeService(ScreenTypeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<ScreenType> getEntityClass() {
        return ScreenType.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    boolean isScreenTypeAlreadyExists(ScreenType screenType) throws CustomException {
        if (screenType.getId() != null) {
            if (repo.findByNameAndId(screenType.getName(), screenType.getId()).isEmpty()) {
                return isNameUsed(screenType.getName());
            }
            return false;
        }
        return isNameUsed(screenType.getName());
    }

    boolean isNameUsed(String name) throws CustomException {
        if (!repo.findByName(name).isEmpty()) {
            throw new CustomException("Ce nom est déjà utilisé");
        }
        return false;
    }

    @Override
    public ScreenType create(ScreenType obj) throws Exception {
        if (!isScreenTypeAlreadyExists(obj)) {
            return super.create(obj);
        }
        return null;
    }

    @Override
    public ScreenType update(ScreenType obj) throws Exception {
        if (!isScreenTypeAlreadyExists(obj)) {
            return super.update(obj);
        }
        return null;
    }

}
