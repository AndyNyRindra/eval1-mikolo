package com.eval1.services;

import com.eval1.repositories.ComissionRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.comission.Comission;

import java.util.List;


@Service
public class ComissionService extends CrudService<Comission, ComissionRepo> {

    public ComissionService(ComissionRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Comission> getEntityClass() {
        return Comission.class;
    }

    public Integer getRequiredPages (Long count) {
//        System.out.println(getComissions(2000000.0));
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    public Double getComissions(Double amount) {
        List<Comission> comissions = repo.findAllOrder();
        Double comission = 0.0;
        Double alreadyCounted = 0.0;
        for (Comission c : comissions) {

            if (amount >= c.getMinValue() && amount <= c.getMaxValue()) {
                comission += (amount - alreadyCounted) * c.getPercent() / 100;
                break;
            } else if (amount > c.getMaxValue()) {
                comission += c.getMaxValue() * c.getPercent() / 100;
            }
            alreadyCounted += c.getMaxValue();
        }
        return comission;
    }

}
