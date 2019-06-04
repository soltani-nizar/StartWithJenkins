package fr.sparkit.crm.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityHistoryStatusDto {
    private Long idUser;
    private String oldStatus;
    private String newStatus;
    private Long opportunityId;
    private String opportunityTitle;
    private Date updatedDate;

}