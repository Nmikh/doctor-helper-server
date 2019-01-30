package com.DAO;

import com.models.entity.doctor.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientPaginationRepository extends PagingAndSortingRepository<PatientEntity, Long> {

    Page<PatientEntity> findAllByOrderBySurnameAscNameAsc(Pageable pageable);

    Page<PatientEntity> findByNameContainingAndSurnameContainingOrderBySurnameAscNameAsc(
            String nameLike, String surnameLike, Pageable pageable);
}
