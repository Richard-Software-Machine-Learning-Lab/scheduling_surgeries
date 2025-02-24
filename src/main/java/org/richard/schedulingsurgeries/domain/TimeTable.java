package org.richard.schedulingsurgeries.domain;


import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@PlanningSolution
public class TimeTable {

    @ValueRangeProvider(id = "startingSurgeryRange")
    @ProblemFactCollectionProperty
    private List<LocalDateTime> startingTimeSurgeries;
    @ValueRangeProvider(id = "roomRange")
    @ProblemFactCollectionProperty
    private List<OperatingRoom> operatingRooms;
    @PlanningEntityCollectionProperty
    private List<Surgery> surgeries;
    @PlanningScore
    private HardSoftScore scoreSolver;
    private SolverStatus solverStatus;

    public TimeTable(){
    }

    public TimeTable(List<OperatingRoom> operatingRooms, List<Surgery> surgeries) {
        this.operatingRooms = operatingRooms;
        this.surgeries = surgeries;
        setStartingTimeSurgeries(operatingRooms.get(0).getOpeningTime(), getOperatingRooms().get(0).getClosingTime());
    }

    public List<LocalDateTime> findStartingTimeSurgeries() {
        return startingTimeSurgeries;
    }

    public void setStartingTimeSurgeries(LocalDateTime openingOperatingRoom, LocalDateTime closingOperationRoom ) {
        this.startingTimeSurgeries = new ArrayList<>();
        while (openingOperatingRoom .isBefore(closingOperationRoom)) {
            startingTimeSurgeries.add(openingOperatingRoom );
            openingOperatingRoom  = openingOperatingRoom .plusMinutes(30);
        }
    }

    public List<OperatingRoom> getOperatingRooms() {
        return operatingRooms;
    }

    public void setOperatingRooms(List<OperatingRoom> operatingRooms) {
        this.operatingRooms = operatingRooms;
    }

    public List<Surgery> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(List<Surgery> surgeries) {
        this.surgeries = surgeries;
    }

    public HardSoftScore getScoreSolver() {
        return scoreSolver;
    }

    public void setScoreSolver(HardSoftScore scoreSolver) {
        this.scoreSolver = scoreSolver;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

}
