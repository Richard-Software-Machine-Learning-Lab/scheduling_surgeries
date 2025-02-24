package org.richard.schedulingsurgeries.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Surgeon extends PanacheEntityBase {
    @PlanningId
    @Id
    private Long surgeonId;
    private String firstName;
    private String lastName;
    @ManyToOne
    private Speciality speciality;

    public Surgeon(){}

    public Surgeon(long surgeonId, String firstName, String lastName, Speciality speciality) {
        this.surgeonId = surgeonId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
    }

    public Long getSurgeonId() {
        return surgeonId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Speciality getSpeciality() {
        return speciality;
    }
}
