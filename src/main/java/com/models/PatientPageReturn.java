package com.models;

import com.models.entity.PatientEntity;

import java.util.List;

public class PatientPageReturn {
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
