package fr.sparkit.crm.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Objectif implements Serializable {

    private static final long serialVersionUID = -1992571370571481955L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OBJECTIF_USER", joinColumns = {
            @JoinColumn(name = "objectif_id", referencedColumnName = "ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "ID") }, uniqueConstraints = {
                            @UniqueConstraint(columnNames = { "objectif_id", "user_id" }) })
    private List<UserCrm> responsablesUsers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OBJECTIF_EMPLOYEE", joinColumns = {
            @JoinColumn(name = "objectif_id", referencedColumnName = "ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "employee_id", referencedColumnName = "ID") }, uniqueConstraints = {
                            @UniqueConstraint(columnNames = { "objectif_id", "employee_id" }) })
    private List<Employee> employees;

    private String type;

    private boolean isDeleted;
    private UUID deletedToken;
}
