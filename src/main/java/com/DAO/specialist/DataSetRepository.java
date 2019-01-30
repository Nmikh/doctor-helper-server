package com.DAO.specialist;

import com.models.entity.specialist.DatasetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DataSetRepository extends JpaRepository<DatasetEntity, Long> {

    @Transactional(readOnly = true)
    Optional<DatasetEntity> findById(Long Id);
}
