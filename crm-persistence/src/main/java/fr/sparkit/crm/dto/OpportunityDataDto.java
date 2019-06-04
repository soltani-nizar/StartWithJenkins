package fr.sparkit.crm.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpportunityDataDto {

    private Map<String, String> statusColors;
    private Map<String, List<OpportunityDto>> statusWithOpportunity;
    private Map<String, Integer> statusPosition;
    private Map<String, List<Long>> objectifUsersId;

    public OpportunityDataDto(Map<String, String> statusColors, Map<String, List<OpportunityDto>> statusWithOpportunity,
            Map<String, Integer> statusPosition, Map<String, List<Long>> objectifUsersId) {
        this.statusColors = statusColors;
        this.statusWithOpportunity = statusWithOpportunity;
        this.statusPosition = statusPosition.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        this.objectifUsersId = objectifUsersId;
    }

}
