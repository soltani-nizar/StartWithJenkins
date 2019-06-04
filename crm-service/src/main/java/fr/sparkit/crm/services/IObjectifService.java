package fr.sparkit.crm.services;

import fr.sparkit.crm.entities.Objectif;

public interface IObjectifService extends IGenericService<Objectif, Long> {

    Objectif findByType(String type);

}
