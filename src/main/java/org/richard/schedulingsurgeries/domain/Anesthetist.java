package org.richard.schedulingsurgeries.domain;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Anesthetist extends PanacheEntityBase {
    @PlanningId
    @Id
    private Long anesthetistId;
    private String firstName;
    private String lastName;

    public Anesthetist(){}

    public Anesthetist(long anesthetistId, String firstName, String lastName) {
        this.anesthetistId = anesthetistId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getAnesthetistId() {
        return anesthetistId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
