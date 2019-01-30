package com.services.doctor;

import com.DAO.doctor.PatientRepository;
import com.DAO.doctor.RecordRepository;
import com.models.entity.doctor.PatientEntity;
import com.models.entity.doctor.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    PatientRepository patientRepository;

    public void createRecord(Long patientId, RecordEntity recordEntity){
        PatientEntity patient = patientRepository.getOne(patientId);

        RecordEntity record = patient.getRecord();

        record.setBirthday(recordEntity.getBirthday());
        record.setBloodGroup(recordEntity.getBloodGroup());
        record.setHeight(recordEntity.getHeight());
        record.setWeight(recordEntity.getWeight());
        record.setSex(recordEntity.getSex());

        recordRepository.save(record);
    }

    public RecordEntity findRecordByPatientId(Long patientId){
        PatientEntity patient = patientRepository.getOne(patientId);

        return patient.getRecord();
    }
}
