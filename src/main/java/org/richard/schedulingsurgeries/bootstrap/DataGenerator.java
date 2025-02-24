package org.richard.schedulingsurgeries.bootstrap;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import org.richard.schedulingsurgeries.domain.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DataGenerator {
    @Transactional
    public void generateData(@Observes StartupEvent startupEvent) {
        LocalDateTime openingOperatingRoom = LocalDateTime.of(2019, 11, 4, 7, 30);
        LocalDateTime closingOperationRoom = LocalDateTime.of(2019, 11, 8, 20, 30);

        List<Time> times = new ArrayList<>();
        times.add(new Time(openingOperatingRoom,closingOperationRoom));
        Time.persist(times);
        List<Insurance> insurances = new ArrayList<>();
        insurances.add(new Insurance("Aetna"));
        insurances.add(new Insurance("Premera"));
        Insurance.persist(insurances);
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(10000,"NA","NA",insurances.get(0)));
        Patient.persist(patients);

        List<SurgeryType> surgeryTypeList = new ArrayList<>();
        surgeryTypeList.add(new SurgeryType("E", "Elective"));
        surgeryTypeList.add(new SurgeryType("A", "Non-Elective"));
        Surgery.persist(surgeryTypeList);

        List<Anesthetist> anesthetists = new ArrayList<>();
        anesthetists.add(new Anesthetist(151, "NA","NA"));
        Anesthetist.persist(anesthetists);

        List<AnesthesiaType> anesthesiaTypes = new ArrayList<>();
        anesthesiaTypes.add(new AnesthesiaType(1));
        AnesthesiaType.persist(anesthesiaTypes);

        List<Speciality> specialities = new ArrayList<>();
        specialities.add(new Speciality("Cardiology"));
        Speciality.persist(specialities);

        List<Procedure> procedures = new ArrayList<>();
        procedures.add(new Procedure("CABG", specialities.get(0)));
        Procedure.persist(procedures);

        List<Surgeon> surgeons = new ArrayList<>();
        surgeons.add(new Surgeon(96922,"NA","NA",specialities.get(0)));
        Surgeon.persist(surgeons);

        List <OperatingRoom> operatingRooms = new ArrayList<>();
        operatingRooms.add(new OperatingRoom("Operating Room 1", times.get(0)));
        operatingRooms.add(new OperatingRoom("Operating Room 2", times.get(0)));
        operatingRooms.add(new OperatingRoom("Operating Room 3", times.get(0)));
        operatingRooms.add(new OperatingRoom("Operating Room 4", times.get(0)));
        operatingRooms.add(new OperatingRoom("Operating Room 5", times.get(0)));
        operatingRooms.add(new OperatingRoom("Operating Room 6", times.get(0)));
        operatingRooms.add(new OperatingRoom("Operating Room 7", times.get(0)));
        operatingRooms.add(new OperatingRoom("Operating Room 8", times.get(0)));
        operatingRooms.add(new OperatingRoom("Operating Room 9", times.get(0)));
        operatingRooms.add(new OperatingRoom("Operating Room 10", times.get(0)));
        OperatingRoom.persist(operatingRooms);

        List <Surgery> surgeries = new ArrayList<>();
        surgeries.add(new Surgery(patients.get(0),surgeons.get(0),anesthesiaTypes.get(0),anesthetists.get(0),surgeryTypeList.get(0),procedures.get(0),51));
        Surgery.persist(surgeries);
    }
}
