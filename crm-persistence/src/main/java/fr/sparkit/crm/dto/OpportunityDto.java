package fr.sparkit.crm.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OpportunityDto {
    private Long id;
    private String title;
    private Long responsableUserId;
    private LocalDateTime createdDate;
    private int currentPositionPipe;
    private LocalDateTime opportunityEndDate;
    private Long employeeId;
    private double estimatedIncome;
    private int currencyId;
    private double rating;
    private Long objectifId;
    private String description;

}