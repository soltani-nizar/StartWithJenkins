package fr.sparkit.crm.converter;

import java.util.ArrayList;
import java.util.List;

import fr.sparkit.crm.dto.ObjectifStatusDto;
import fr.sparkit.crm.dto.ObjectiveStatusIndexDto;
import fr.sparkit.crm.entities.Objectif;
import fr.sparkit.crm.entities.ObjectifStatus;

public final class ObjectifStatusConverter {

    public static ObjectifStatusDto modelToDto(Objectif objectif, List<ObjectifStatus> listStatusByObjectif) {
        ObjectifStatusDto objStatusDto = new ObjectifStatusDto();
        List<Long> employeesId = new ArrayList<>();
        List<Long> responsablesUsersId = new ArrayList<>();
        List<ObjectiveStatusIndexDto> objectiveStatusIndexDto = new ArrayList<>();

        objectif.getEmployees().forEach(e -> {
            employeesId.add(e.getId());
        });
        objectif.getResponsablesUsers().forEach(u -> {
            responsablesUsersId.add(u.getId());
        });
        objStatusDto.setId(objectif.getId());
        objStatusDto.setTitle(objectif.getTitle());
        objStatusDto.setEmployeesId(employeesId);
        objStatusDto.setResponsablesUsersId(responsablesUsersId);
        objStatusDto.setObjectifType(objectif.getType());
        listStatusByObjectif.forEach(e -> objectiveStatusIndexDto
                .add(new ObjectiveStatusIndexDto(e.getStatus().getId(), e.getPositionInPipe())));
        objStatusDto.setObjectiveStatusIndexDto(objectiveStatusIndexDto);
        return objStatusDto;
    }

}
