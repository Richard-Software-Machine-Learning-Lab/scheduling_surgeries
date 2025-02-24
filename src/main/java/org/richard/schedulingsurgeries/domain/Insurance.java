package org.richard.schedulingsurgeries.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Insurance extends PanacheEntityBase {
    @PlanningId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceId;
    private String name;

    public Insurance(){};

    public Insurance(String name) {
        this.name = name;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public String getName() {
        return name;
    }
}
