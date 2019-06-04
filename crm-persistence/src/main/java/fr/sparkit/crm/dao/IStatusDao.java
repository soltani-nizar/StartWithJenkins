package fr.sparkit.crm.dao;

import org.springframework.stereotype.Repository;

import fr.sparkit.crm.entities.Status;

@Repository
public interface IStatusDao extends BaseRepository<Status, Long> {

    Status findByColorOrTitleAndIsDeletedFalse(String color, String title);

}
