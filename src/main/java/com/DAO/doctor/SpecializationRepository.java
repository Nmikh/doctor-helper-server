package com.DAO.doctor;

import com.models.entity.doctor.SpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<SpecializationEntity, Long> {

    @Transactional(readOnly = true)
    @Override
    SpecializationEntity getOne(Long id);

    @Transactional(readOnly = true)
    @Override
    List<SpecializationEntity> findAll();
}
