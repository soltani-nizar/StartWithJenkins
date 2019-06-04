package fr.sparkit.crm.converter;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import fr.sparkit.crm.dto.StatusDto;
import fr.sparkit.crm.entities.Status;

public final class StatusConvertor {

    private StatusConvertor() {
        super();
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static StatusDto modelToDto(Status status) {
        StatusDto statusDto = modelMapper.map((Object) status, StatusDto.class);
        statusDto.setTitle(status.getTitle());
        statusDto.setColor(status.getColor());
        return statusDto;
    }

    public static List<StatusDto> listModelToListDto(List<Status> oportunityList) {
        return oportunityList.stream().map(StatusConvertor::modelToDto).collect(Collectors.toList());
    }

    public static Status dtoToModel(StatusDto statusDto) {

        return new Status(statusDto.getColor(), statusDto.getTitle());
    }

    public static List<StatusDto> modelsToDtos(Collection<Status> statuts) {
        return statuts.stream().filter(Objects::nonNull).map(StatusConvertor::modelToDto).collect(Collectors.toList());
    }
}
