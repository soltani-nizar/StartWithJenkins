package fr.sparkit.crm.services;

import java.util.List;

import fr.sparkit.crm.entities.ObjectifStatus;

public interface IObjectifStatusService extends IGenericService<ObjectifStatus, Long> {
    List<ObjectifStatus> findByObjectifId(Long id);

    List<ObjectifStatus> findByObjectifType(String type);

}
