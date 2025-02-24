package org.richard.schedulingsurgeries.view;


public class ListInformationSurgeriesView {
    private int surgeryId;
    private int patientId;
    private int surgeonId;
    private String specialityName;
    private String procedure;
    private int duration;


    public ListInformationSurgeriesView(int surgeryId, int patientId, int surgeonId, String specialityName, String procedure,
                                        int duration) {
        this.surgeryId = surgeryId;
        this.patientId = patientId;
        this.surgeonId = surgeonId;
        this.specialityName = specialityName;
        this.procedure = procedure;
        this.duration = duration;
    }

    public int getSurgeryId() {
        return surgeryId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getSurgeonId() {
        return surgeonId;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public String getProcedure() {
        return procedure;
    }

    public int getDuration() {
        return duration;
    }
}

