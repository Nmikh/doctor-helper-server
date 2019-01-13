package com.DAO;

import com.models.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    List<PatientEntity> findByNameLikeAndSurnameLikeOrderBySurname(
            String nameLike, String surnameLike);
}
