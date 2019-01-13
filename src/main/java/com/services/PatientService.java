package com.services;

import com.DAO.PatientRepository;
import com.DAO.RecordRepository;
import com.models.entity.PatientEntity;
import com.models.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RecordRepository recordRepository;

    public Long createPatient(PatientEntity patient){

        PatientEntity patientSave = patientRepository.save(patient);

        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setPatient(patientSave);

        recordRepository.save(recordEntity);

        return patientSave.getId();
    }


}
