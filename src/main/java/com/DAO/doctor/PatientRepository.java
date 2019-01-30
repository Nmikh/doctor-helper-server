package com.DAO.doctor;

import com.models.entity.doctor.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    @Transactional(readOnly = true)
    List<PatientEntity> findByNameContainingAndSurnameContainingOrderBySurnameAscNameAsc(
            String nameLike, String surnameLike);

    @Transactional(readOnly = true)
    List<PatientEntity> findAllByOrderBySurnameAscNameAsc();

    @Transactional(readOnly = true)
    @Override
    PatientEntity getOne(Long id);

    @Transactional(readOnly = true)
    Optional<PatientEntity> findById(Long id);


}
