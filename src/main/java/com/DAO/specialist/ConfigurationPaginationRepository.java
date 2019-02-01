package com.DAO.specialist;

import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationPaginationRepository extends PagingAndSortingRepository<DatasetConfigurationEntity, Long> {

    Page<DatasetConfigurationEntity> findByDatasetEntityOrderByNameAsc(DatasetEntity datasetEntity, Pageable pageable);
}
