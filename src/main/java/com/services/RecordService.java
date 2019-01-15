package com.services;

import com.DAO.PatientRepository;
import com.DAO.RecordRepository;
import com.models.entity.PatientEntity;
import com.models.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    PatientRepository patientRepository;

    public Long createRecord(Long patientId, RecordEntity recordEntity){
        PatientEntity patient = patientRepository.getOne(patientId);

        RecordEntity record = patient.getRecord();

        record.setBirthday(recordEntity.getBirthday());
        record.setBloodGroup(recordEntity.getBloodGroup());
        record.setHeight(recordEntity.getHeight());
        record.setWeight(recordEntity.getWeight());
        record.setSex(recordEntity.getSex());

        recordRepository.save(record);

        return record.getId();
    }
}
