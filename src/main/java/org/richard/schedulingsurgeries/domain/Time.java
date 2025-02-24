package org.richard.schedulingsurgeries.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Time extends PanacheEntityBase {
    @PlanningId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeId;
    private LocalDateTime startingTimeWeekly;
    private LocalDateTime finishTimeWeekly ;

    public Time(){
    }

    public Time(LocalDateTime startingTimeWeekly, LocalDateTime finishTimeWeekly) {
        this.startingTimeWeekly = startingTimeWeekly;
        this.finishTimeWeekly = finishTimeWeekly;
    }

    public Long getTimeId() {
        return timeId;
    }

    public LocalDateTime getStartingTimeWeekly() {
        return startingTimeWeekly;
    }

    public LocalDateTime getFinishTimeWeekly() {
        return finishTimeWeekly;
    }

    public void setStartingTimeWeekly(LocalDateTime startingTimeWeekly) {
        this.startingTimeWeekly = startingTimeWeekly;
    }

    public void setFinishTimeWeekly(LocalDateTime finishTimeWeekly) {
        this.finishTimeWeekly = finishTimeWeekly;
    }
}
