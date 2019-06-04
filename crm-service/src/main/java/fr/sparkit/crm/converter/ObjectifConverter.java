package fr.sparkit.crm.converter;

import java.util.List;

import fr.sparkit.crm.dto.ObjectifStatusDto;
import fr.sparkit.crm.entities.Employee;
import fr.sparkit.crm.entities.Objectif;
import fr.sparkit.crm.entities.UserCrm;

public final class ObjectifConverter {

    public static Objectif dtoToModel(Objectif obj, ObjectifStatusDto objStatusDto, List<Employee> employees,
            List<UserCrm> responsableUsers) {
        obj.setTitle(objStatusDto.getTitle());
        obj.setType(objStatusDto.getObjectifType());
        obj.setEmployees(employees);
        obj.setResponsablesUsers(responsableUsers);
        return obj;

    }

}
