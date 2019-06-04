package fr.sparkit.crm.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sparkit.crm.dao.IObjectifDao;
import fr.sparkit.crm.entities.Objectif;
import fr.sparkit.crm.services.IObjectifService;

@Service
public class ObjectifServiceImpl extends GenericService<Objectif, Long> implements IObjectifService {

    @Autowired
    private IObjectifDao objectifDao;

    @Override
    public Objectif findByType(String type) {
        return objectifDao.findByType(type);
    }

}
