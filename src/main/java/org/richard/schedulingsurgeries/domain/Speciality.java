package org.richard.schedulingsurgeries.domain;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Speciality extends PanacheEntityBase {
    @PlanningId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialityId;
    private String name;

    public Speciality(){}

    public Speciality(String name) {
        this.name = name;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public String getName() {
        return name;
    }
}
