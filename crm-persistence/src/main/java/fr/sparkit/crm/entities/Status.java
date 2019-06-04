package fr.sparkit.crm.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Status")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Status implements Serializable {

    private static final long serialVersionUID = -5883364877326695622L;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Color")
    private String color;

    @Column(name = "IsDeleted", columnDefinition = "bit default 0")
    private boolean isDeleted;

    @Column(name = "DeletedToken")
    private UUID deletedToken;

    public Status(String color, String title) {
        this.color = color;
        this.title = title;
    }

}
