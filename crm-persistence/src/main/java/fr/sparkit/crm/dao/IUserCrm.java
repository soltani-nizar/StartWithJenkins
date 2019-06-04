package fr.sparkit.crm.dao;

import org.springframework.stereotype.Repository;

import fr.sparkit.crm.entities.UserCrm;

@Repository
public interface IUserCrm extends BaseRepository<UserCrm, Long> {

}
