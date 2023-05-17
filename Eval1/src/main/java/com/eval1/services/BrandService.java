package com.eval1.services;

import com.eval1.repositories.BrandRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import com.eval1.models.brand.Brand;


@Service
public class BrandService extends CrudService<Brand, BrandRepo> {

    public BrandService(BrandRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Brand> getEntityClass() {
        return Brand.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    boolean isBrandAlreadyExists(Brand brand) throws CustomException {
        if (brand.getId() != null) {
            if (repo.findByNameAndId(brand.getName(), brand.getId()).isEmpty()) {
                return isNameUsed(brand.getName());
            }
            return false;
        }
        return isNameUsed(brand.getName());
    }

    boolean isNameUsed(String name) throws CustomException {
        if (!repo.findByName(name).isEmpty()) {
            throw new CustomException("Ce nom est déjà utilisé");
        }
        return false;
    }

    @Override
    public Brand create(Brand obj) throws Exception {
        if (!isBrandAlreadyExists(obj)) {
            return super.create(obj);
        }
        return null;
    }

    @Override
    public Brand update(Brand obj) throws Exception {
        if (!isBrandAlreadyExists(obj)) {
            return super.update(obj);
        }
        return null;
    }

}
