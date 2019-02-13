package com.conformal_predictors.DAO;

import com.conformal_predictors.models.entity.ConfigurationResultEntity;
import com.models.entity.specialist.DatasetConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ConfigurationResultRepository extends JpaRepository<ConfigurationResultEntity, Long> {
    @Transactional
    void deleteAllByDatasetConfigurationEntity(DatasetConfigurationEntity datasetConfigurationEntity);
}
