package com.services.doctor;

import com.DAO.doctor.PatientPaginationRepository;
import com.DAO.doctor.PatientRepository;
import com.DAO.doctor.RecordRepository;
import com.models.doctor.PatientPageReturn;
import com.models.entity.doctor.PatientEntity;
import com.models.entity.doctor.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    PatientPaginationRepository patientPaginationRepository;

    public Long createPatient(PatientEntity patient) {

        PatientEntity patientSave = patientRepository.save(patient);

        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setPatient(patientSave);

        recordRepository.save(recordEntity);

        return patientSave.getId();
    }

    public List<PatientEntity> getPatientByNameAndSurnameLike(String nameLike, String surnameLike) {
        return patientRepository.findByNameContainingAndSurnameContainingOrderBySurnameAscNameAsc(nameLike, surnameLike);
    }

    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    public PatientEntity getPatient(Long patientId) {
        return patientRepository.findById(patientId).get();
    }

    public void changePatient(Long patientId, PatientEntity patientEntity) {
        PatientEntity patient = patientRepository.findById(patientId).get();

        patient.setName(patientEntity.getName());
        patient.setSurname(patientEntity.getSurname());
        patient.setAddress(patientEntity.getAddress());
        patient.setTelephone(patientEntity.getTelephone());
        patient.setEmail(patientEntity.getEmail());

        patientRepository.save(patient);
    }

    public PatientPageReturn getPageOfPatients(int page, int objectsOnPage) {
        Page<PatientEntity> allPatientsOrderBySurname =
                patientPaginationRepository.findAllByOrderBySurnameAscNameAsc(new PageRequest(--page, objectsOnPage));

        return new PatientPageReturn(allPatientsOrderBySurname.getTotalPages(),
                allPatientsOrderBySurname.getContent());
    }

    public PatientPageReturn getPatientsPageByNameAndSurnameLike(String nameLike, String surnameLike, int page, int objectsOnPage) {
        Page<PatientEntity> allPatients = patientPaginationRepository.findByNameContainingAndSurnameContainingOrderBySurnameAscNameAsc(
                nameLike, surnameLike, new PageRequest(--page, objectsOnPage));

        return new PatientPageReturn(allPatients.getTotalPages(), allPatients.getContent());
    }
}
