package com.services;

import com.DAO.PatientRepository;
import com.DAO.RecordRepository;
import com.models.entity.PatientEntity;
import com.models.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RecordRepository recordRepository;

    public Long createPatient(PatientEntity patient) {

        PatientEntity patientSave = patientRepository.save(patient);

        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setPatient(patientSave);

        recordRepository.save(recordEntity);

        return patientSave.getId();
    }

    public List<PatientEntity> getPatientByNameAndSurnameLike(String nameLike, String surnameLike) {
        return patientRepository.findByNameContainingAndSurnameContainingOrderBySurname(nameLike, surnameLike);
    }

    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    public PatientEntity getPatient(Long patientId) {
        return patientRepository.findById(patientId).get();
    }

    public void changePatient(Long patientId, PatientEntity patientEntity){
        PatientEntity patient = patientRepository.findById(patientId).get();

        patient.setName(patientEntity.getName());
        patient.setSurname(patientEntity.getSurname());
        patient.setAddress(patientEntity.getAddress());
        patient.setTelephone(patientEntity.getTelephone());
        patient.setEmail(patientEntity.getEmail());

        patientRepository.save(patient);
    }

}
