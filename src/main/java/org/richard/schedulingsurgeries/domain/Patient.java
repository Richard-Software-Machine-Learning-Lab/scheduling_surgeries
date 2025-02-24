package org.richard.schedulingsurgeries.domain;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Patient extends PanacheEntityBase {
    @PlanningId
    @Id
    private Long patientId;
    private String firstName;
    private String lastName;
    @OneToOne
    private Insurance insurance;

    public Patient(){
    }

    public Patient(long patientId, String firstName, String lastName, Insurance insurance) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.insurance = insurance;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

}
