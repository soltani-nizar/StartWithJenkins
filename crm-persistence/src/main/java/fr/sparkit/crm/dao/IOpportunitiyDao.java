package fr.sparkit.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.sparkit.crm.entities.Opportunity;

@Repository
public interface IOpportunitiyDao extends BaseRepository<Opportunity, Long> {

    List<Opportunity> findByObjectifTypeAndResponsableUserId(String objectifType, Long id);
    List<Opportunity> findByObjectifTypeAndEmployeeId(String objectifType, Long id);

}
