package fr.sparkit.crm.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import fr.sparkit.crm.dto.OpportunityDto;
import fr.sparkit.crm.entities.Employee;
import fr.sparkit.crm.entities.Objectif;
import fr.sparkit.crm.entities.Opportunity;
import fr.sparkit.crm.entities.UserCrm;

public final class OpportunityConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public static OpportunityDto modelToDto(Opportunity opportunity) {
        OpportunityDto opportunityDto = modelMapper.map(opportunity, OpportunityDto.class);
        opportunityDto.setEmployeeId(opportunity.getEmployee().getId());
        opportunityDto.setResponsableUserId(opportunity.getResponsableUser().getId());
        opportunityDto.setCreatedDate(opportunity.getCreatedDate());
        opportunityDto.setObjectifId(opportunity.getObjectif().getId());

        return opportunityDto;
    }

    public static Opportunity dtoToModel(Opportunity opportunity, OpportunityDto opportunityDto,
            UserCrm responsableUser, Objectif objectif, Employee employee) {
        modelMapper.map(opportunityDto, opportunity);
        opportunity.setResponsableUser(responsableUser);
        opportunity.setObjectif(objectif);
        opportunity.setEmployee(employee);
        return opportunity;
    }

    public static List<OpportunityDto> ListModelToListDto(List<Opportunity> oportunityList) {
        return oportunityList.stream().map(OpportunityConverter::modelToDto).collect(Collectors.toList());
    }
}