package fr.sparkit.crm.dao;

import org.springframework.stereotype.Repository;

import fr.sparkit.crm.entities.Objectif;

@Repository
public interface IObjectifDao extends BaseRepository<Objectif, Long> {

    Objectif findByType(String type);
}
