package com.DAO;

import com.models.entity.SpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<SpecializationEntity, Long> {

    @Override
    SpecializationEntity getOne(Long id);

    @Override
    List<SpecializationEntity> findAll();
}
