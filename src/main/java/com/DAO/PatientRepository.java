package com.DAO;

import com.models.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    @Transactional(readOnly = true)
    List<PatientEntity> findByNameContainingAndSurnameContainingOrderBySurname(
            String nameLike, String surnameLike);

    @Transactional(readOnly = true)
    List<PatientEntity> findAll();

    @Transactional(readOnly = true)
    @Override
    PatientEntity getOne(Long id);

    @Transactional(readOnly = true)
    Optional<PatientEntity> findById(Long id);


}
