package com.conformal_predictors.DAO;

import com.conformal_predictors.models.entity.ConfigurationResultEntity;
import com.models.entity.specialist.DatasetConfigurationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConfigurationResultPaginationRepository extends PagingAndSortingRepository<ConfigurationResultEntity, Long> {
    Page<ConfigurationResultEntity> findByDatasetConfigurationEntityOrderById(DatasetConfigurationEntity datasetConfigurationEntity, Pageable pageable);
}
