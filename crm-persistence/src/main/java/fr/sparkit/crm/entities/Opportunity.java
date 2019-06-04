package fr.sparkit.crm.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.sparkit.crm.auditing.LocalDateTimeAttributeConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({ "createdBy", "lastModifiedBy", "lastModifiedDate", "createdDate" })
@Table(name = "Opportunity", indexes = { @Index(name = "Id_Currency_Index", columnList = "currencyId") })
public class Opportunity extends AbstractAuditEntity {

    private static final long serialVersionUID = 2536682530899333878L;

    private String title;
    @ManyToOne(optional = false)
    private UserCrm responsableUser;
    @ManyToOne
    private Objectif objectif;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime CreatedDate;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime opportunityEndDate;
    private double estimatedIncome;
    private int currencyId;
    private double rating;
    private int currentPositionPipe;
    private int closedPositionPipe;
    private boolean isDeleted;
    private UUID deletedToken;
    @ManyToOne
    private Employee employee;
    private String description;

    @Override
    public String entityIdentifier() {

        return title;
    }

}