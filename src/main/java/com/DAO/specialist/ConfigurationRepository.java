package com.DAO.specialist;

import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<DatasetConfigurationEntity, Long> {
    @Override
    Optional<DatasetConfigurationEntity> findById(Long aLong);

    List<DatasetConfigurationEntity> findByDatasetEntityOrderByNameAsc(DatasetEntity datasetEntity);
}
