package fr.sparkit.crm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sparkit.crm.dao.IObjectifStatusDao;
import fr.sparkit.crm.entities.ObjectifStatus;
import fr.sparkit.crm.services.IObjectifStatusService;

@Service
public class ObjectifStatusServiceImpl extends GenericService<ObjectifStatus, Long> implements IObjectifStatusService {

    @Autowired
    private IObjectifStatusDao objStatusDao;

    @Override
    public List<ObjectifStatus> findByObjectifId(Long id) {
        return objStatusDao.findByObjectifId(id);
    }

    @Override
    public List<ObjectifStatus> findByObjectifType(String type) {
        return objStatusDao.findByObjectifType(type);
    }

}
