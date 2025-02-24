package org.richard.schedulingsurgeries.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnesthesiaType extends PanacheEntityBase {
    @PlanningId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long anesthesiaId;
    private int code;

    public AnesthesiaType(){}

    public AnesthesiaType(int code) {
        this.code = code;
    }

    public Long getAnesthesiaId() {
        return anesthesiaId;
    }

    public int getCode() {
        return code;
    }
}
