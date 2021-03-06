package com.DAO.doctor;

import com.models.entity.doctor.PatientEntity;
import com.models.entity.doctor.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

    @Transactional(readOnly = true)
    RecordEntity findByPatient(PatientEntity patientEntity);

    @Transactional(readOnly = true)
    @Override
    RecordEntity getOne(Long id);

}
