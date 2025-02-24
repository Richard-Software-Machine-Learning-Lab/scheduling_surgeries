package org.richard.schedulingsurgeries.solver;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;
import org.richard.schedulingsurgeries.domain.Surgery;

public class TimeTableConstraintProvider  implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                operatingRoomConflict(constraintFactory),
                availableTimeOperatingTheater(constraintFactory)
        };
    }

    public Constraint operatingRoomConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Surgery.class)
                .join(Surgery.class,
                        Joiners.equal(Surgery::getOperatingRoom),
                        Joiners.overlapping(Surgery::getStartingTimeSurgery, Surgery::getFinishingTimeSurgery),
                        Joiners.lessThan(Surgery::getSurgeryId))
                .penalize("Conflict operating room", HardSoftScore.ONE_HARD);
    }


    public Constraint availableTimeOperatingTheater(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Surgery.class)
                .filter((surgery -> surgery.availableTime() == false))
                .penalize("Conflict unavailable time", HardSoftScore.ONE_HARD);
    }
}
