package org.richard.schedulingsurgeries.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SurgeryType extends PanacheEntityBase {
    @PlanningId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surgeryTypeId;
    private String code;
    private String name;

    public SurgeryType(){}

    public SurgeryType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getSurgeryTypeId() {
        return surgeryTypeId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
