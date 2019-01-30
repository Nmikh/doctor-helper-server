package com.services.doctor;

import com.DAO.doctor.SpecializationRepository;
import com.models.entity.doctor.SpecializationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    @Autowired
    SpecializationRepository specializationRepository;

    public List<SpecializationEntity> getAllSpecializations(){
        return specializationRepository.findAll();
    }
}
