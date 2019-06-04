package fr.sparkit.crm.services.impl;

import org.springframework.stereotype.Service;

import fr.sparkit.crm.entities.Employee;
import fr.sparkit.crm.services.IEmployeeService;

@Service
public class EmployeeServiceImpl extends GenericService<Employee, Long> implements IEmployeeService {

}
