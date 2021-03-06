package com.models.doctor;

import com.models.entity.doctor.PatientEntity;

import java.io.Serializable;
import java.util.List;

public class PatientPageReturn implements Serializable {
    private int numberOfPages;
    private List<PatientEntity> patientEntities;

    public PatientPageReturn(int numberOfPages, List<PatientEntity> patientEntities) {
        this.numberOfPages = numberOfPages;
        this.patientEntities = patientEntities;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<PatientEntity> getPatientEntities() {
        return patientEntities;
    }

    public void setPatientEntities(List<PatientEntity> patientEntities) {
        this.patientEntities = patientEntities;
    }
}
