package fr.sparkit.crm.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

import fr.sparkit.crm.auditing.LocalDateTimeAttributeConverter;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EntityHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "entity_type", nullable = false)
    private Class<?> entityType;

    @Size(max = 20)
    @Column(name = "action", nullable = false, length = 20)
    private String action;

    @Lob
    @Column(name = "entity_identifier", nullable = false)
    private String entityIdentifier;
    @Lob
    @Column(name = "entity_value")
    private String entityValue;

    @Column(name = "modified_by", length = 20)
    private String modifiedBy;

    @Column(name = "modified_Date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime modifiedDate;

    @Lob
    @Column(name = "modification")
    private String modification;

}
