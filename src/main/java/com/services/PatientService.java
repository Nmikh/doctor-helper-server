package com.services;

import com.DAO.PatientRepository;
import com.models.entity.PatientEntity;
import com.models.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public PatientEntity createPatient(PatientEntity patient){

        RecordEntity recordEntity = new RecordEntity();

        //recordEntity.setPatient(patient);
        patient.setRecord(recordEntity);

        patientRepository.save(patient);

        return patient;
    }


}
