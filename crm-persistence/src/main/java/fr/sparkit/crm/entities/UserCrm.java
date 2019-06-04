package fr.sparkit.crm.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserCrm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToMany(mappedBy = "responsablesUsers")
    private List<Objectif> objectifs;

    private boolean isDeleted;

    private UUID deletedToken;

    public UserCrm(Long id) {
        this.id = id;
    }

}
