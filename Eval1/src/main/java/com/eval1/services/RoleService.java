package com.eval1.services;

import com.eval1.repositories.RoleRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import com.eval1.models.Role;


@Service
public class RoleService extends CrudService<Role, RoleRepo> {

    public RoleService(RoleRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Role> getEntityClass() {
        return Role.class;
    }

}
