package org.richard.schedulingsurgeries.solver;

import org.junit.jupiter.api.Test;
import org.optaplanner.test.api.score.stream.ConstraintVerifier;
import org.richard.schedulingsurgeries.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeTableConstraintProviderTest {

    private final ConstraintVerifier<TimeTableConstraintProvider, TimeTable> constraintVerifier =
            ConstraintVerifier.build(new TimeTableConstraintProvider(), TimeTable.class, Surgery.class);

    @Test
    public void operatingRoomConflict(){
        LocalDateTime openingOperatingRoom = LocalDateTime.of(2019, 11, 4, 7, 30);
        LocalDateTime closingOperationRoom = LocalDateTime.of(2019, 11, 8, 20, 30);
        List<Time> times = new ArrayList<>();
        times.add(new Time(openingOperatingRoom,closingOperationRoom));
        List<Insurance> insurances = new ArrayList<>();
        insurances.add(new Insurance("Particular"));
        insurances.add(new Insurance("Sus"));
        insurances.add(new Insurance("Unimed"));

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(11,"NA","NA", insurances.get(0)));
        patients.add(new Patient(22,"NA","NA", insurances.get(0)));

        List<SurgeryType> surgeryTypeList = new ArrayList<>();
        surgeryTypeList.add(new SurgeryType("E", "Elective"));
        surgeryTypeList.add(new SurgeryType( "A", "Ambulatoria"));

        List<Speciality> specialities = new ArrayList<>();
        specialities.add(new Speciality("Obstetrica"));
        specialities.add(new Speciality("Cirurgia Geral"));
        specialities.add(new Speciality("Ortopedia/Traumatologia"));

        List<Surgeon> surgeons = new ArrayList<>();
        surgeons.add(new Surgeon(198,"NA","NA", specialities.get(0)));
        surgeons.add(new Surgeon(197, "NA", "NA", specialities.get(1)));

        List<AnesthesiaType> anesthesiaTypes = new ArrayList<>();
        anesthesiaTypes.add(new AnesthesiaType(10));
        anesthesiaTypes.add(new AnesthesiaType(16));

        List<Anesthetist> anesthetists = new ArrayList<>();
        anesthetists.add(new Anesthetist(234, "NA","NA"));
        anesthetists.add(new Anesthetist(456, "NA", "NA"));

        List<Procedure> procedures = new ArrayList<>();
        procedures.add(new Procedure("Cesariana (feto unico)", specialities.get(0)));
        procedures.add(new Procedure("Resseccao de Tumor de Partes Moles", specialities.get(1)));
        procedures.add(new Procedure("Descolamento Epifisario do Umero - Tratamento Cirurgico", specialities.get(2)));

        List <OperatingRoom> operatingRooms = new ArrayList<>();
        operatingRooms.add(new OperatingRoom("Centro Cirúrgico Sala 3", times.get(0)));
        operatingRooms.add(new OperatingRoom("Centro Cirúrgico Sala 5", times.get(0)));

        LocalDateTime startTimeSurgery = LocalDateTime.of(2019, 11, 4, 10, 30);
        Surgery scheduledSurgeryOne = new Surgery(patients.get(0), surgeons.get(0), anesthesiaTypes.get(0), anesthetists.get(0),  surgeryTypeList.get(0), procedures.get(0),  40);
        Surgery scheduledSurgeryTwo = new Surgery(patients.get(1), surgeons.get(1), anesthesiaTypes.get(1), anesthetists.get(1),  surgeryTypeList.get(0), procedures.get(1),  40);

        scheduledSurgeryOne.setStartingTimeSurgery(startTimeSurgery);
        scheduledSurgeryOne.setOperatingRoom(operatingRooms.get(0));
        scheduledSurgeryOne.setSurgeryId(11L);
        scheduledSurgeryTwo.setStartingTimeSurgery(startTimeSurgery);
        scheduledSurgeryTwo.setOperatingRoom(operatingRooms.get(0));
        scheduledSurgeryTwo.setSurgeryId(22L);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::operatingRoomConflict)
                .given(scheduledSurgeryOne, scheduledSurgeryTwo)
                .penalizes(1);
    }

    @Test
    public void availableTimeOperatingTheater(){
        LocalDateTime openingOperatingRoom = LocalDateTime.of(2019, 11, 4, 7, 30);
        LocalDateTime closingOperationRoom = LocalDateTime.of(2019, 11, 8, 20, 30);

        List<Time> times = new ArrayList<>();
        times.add(new Time(openingOperatingRoom,closingOperationRoom));

        List<Insurance> insurances = new ArrayList<>();
        insurances.add(new Insurance("Particular"));

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(11,"NA","NA", insurances.get(0)));

        List<SurgeryType> surgeryTypeList = new ArrayList<>();
        surgeryTypeList.add(new SurgeryType("E", "Elective"));

        List<Speciality> specialities = new ArrayList<>();
        specialities.add(new Speciality("Obstetrica"));

        List<Surgeon> surgeons = new ArrayList<>();
        surgeons.add(new Surgeon(198,"NA","NA", specialities.get(0)));

        List<AnesthesiaType> anesthesiaTypes = new ArrayList<>();
        anesthesiaTypes.add(new AnesthesiaType(10));

        List<Anesthetist> anesthetists = new ArrayList<>();
        anesthetists.add(new Anesthetist(234, "NA","NA"));

        List<Procedure> procedures = new ArrayList<>();
        procedures.add(new Procedure("Cesariana (feto unico)", specialities.get(0)));

        List <OperatingRoom> operatingRooms = new ArrayList<>();
        operatingRooms.add(new OperatingRoom("Centro Cirúrgico Sala 3", times.get(0)));

        LocalDateTime startTimeSurgery = LocalDateTime.of(2019, 11, 4, 6, 30);
        Surgery scheduledSurgeryOne = new Surgery(patients.get(0), surgeons.get(0), anesthesiaTypes.get(0), anesthetists.get(0),  surgeryTypeList.get(0), procedures.get(0),  40);

        scheduledSurgeryOne.setStartingTimeSurgery(startTimeSurgery);
        scheduledSurgeryOne.setOperatingRoom(operatingRooms.get(0));
        scheduledSurgeryOne.setSurgeryId(11L);

        constraintVerifier.verifyThat(TimeTableConstraintProvider::availableTimeOperatingTheater)
                .given(scheduledSurgeryOne)
                .penalizes(1);
    }

}
