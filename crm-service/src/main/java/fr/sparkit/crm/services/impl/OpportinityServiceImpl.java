package fr.sparkit.crm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sparkit.crm.dao.IOpportunitiyDao;
import fr.sparkit.crm.entities.Opportunity;
import fr.sparkit.crm.services.IOpportinityService;

@Service
public class OpportinityServiceImpl extends GenericService<Opportunity, Long> implements IOpportinityService {

    @Autowired
    private IOpportunitiyDao opportunityDao;

    @Override
    public List<Opportunity> findByObjectifTypeAndResponsableUserId(String objectifType, Long id) {
        return opportunityDao.findByObjectifTypeAndResponsableUserId(objectifType, id);
    }

    @Override
    public List<Opportunity> findByObjectifTypeAndEmployeeId(String objectifType, Long id) {
        return opportunityDao.findByObjectifTypeAndEmployeeId(objectifType, id);
    }

}
