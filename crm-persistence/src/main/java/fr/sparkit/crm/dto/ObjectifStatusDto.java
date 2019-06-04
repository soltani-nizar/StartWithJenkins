package fr.sparkit.crm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObjectifStatusDto {

    private Long id;
    private String title;
    private String objectifType;
    private List<Long> employeesId;
    private List<Long> responsablesUsersId;
    private List<ObjectiveStatusIndexDto> objectiveStatusIndexDto;
}
