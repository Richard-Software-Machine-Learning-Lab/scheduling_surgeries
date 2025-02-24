package org.richard.schedulingsurgeries.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OperatingRoom extends PanacheEntityBase {
    @PlanningId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operatingRoomId;
    @OneToOne
    private Time time;
    private String operatingRoomName;

    private LocalDateTime startingTimeWeekly;
    private LocalDateTime finishTimeWeekly ;

    public OperatingRoom(){
    }

    public OperatingRoom(String operatingRoomName, Time time) {
        this.operatingRoomName = operatingRoomName;
        this.startingTimeWeekly = time.getStartingTimeWeekly();
        this.finishTimeWeekly = time.getFinishTimeWeekly();
        this.time = time;
    }

    public Long getOperatingRoomId() {
        return operatingRoomId;
    }

    public String getOperatingRoomName() {
        return operatingRoomName;
    }

    public LocalDateTime getOpeningTime() {
        return this.getTime().getStartingTimeWeekly();
    }

    public LocalDateTime getClosingTime() {
        return this.getTime().getFinishTimeWeekly();
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return operatingRoomName;
    }
}
