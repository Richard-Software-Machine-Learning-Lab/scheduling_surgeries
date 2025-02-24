package org.richard.schedulingsurgeries.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.*;

@Entity
public class Procedure extends PanacheEntityBase {
    @PlanningId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long procedureId;
    @ManyToOne
    private Speciality speciality;
    private String name;

    public Procedure(){}

    public Procedure(String name, Speciality speciality) {
        this.speciality = speciality;
        this.name = name;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public String getName() {
        return name;
    }
}
