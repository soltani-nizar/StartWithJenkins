package fr.sparkit.crm.dao;

import org.springframework.stereotype.Repository;

import fr.sparkit.crm.entities.Employee;

@Repository
public interface IEmployeeDao extends BaseRepository<Employee, Long> {

}
