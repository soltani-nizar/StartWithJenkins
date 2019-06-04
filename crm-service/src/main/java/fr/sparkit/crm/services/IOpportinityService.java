package fr.sparkit.crm.services;

import java.util.List;

import fr.sparkit.crm.entities.Opportunity;

public interface IOpportinityService extends IGenericService<Opportunity, Long> {

    List<Opportunity> findByObjectifTypeAndResponsableUserId(String objectifType, Long id);
    List<Opportunity> findByObjectifTypeAndEmployeeId(String objectifType, Long id);

}
