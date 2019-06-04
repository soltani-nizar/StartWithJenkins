package fr.sparkit.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.sparkit.crm.entities.ObjectifStatus;

@Repository
public interface IObjectifStatusDao extends BaseRepository<ObjectifStatus, Long> {
    List<ObjectifStatus> findByObjectifId(Long id);

    List<ObjectifStatus> findByObjectifType(String type);

}
