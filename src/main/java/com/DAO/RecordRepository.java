package com.DAO;

import com.models.entity.PatientEntity;
import com.models.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

    @Transactional(readOnly = true)
    RecordRepository findByPatient(PatientEntity patientEntity);

    @Override
    RecordEntity getOne(Long id);

}
