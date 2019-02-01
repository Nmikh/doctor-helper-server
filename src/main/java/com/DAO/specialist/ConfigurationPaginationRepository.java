package com.DAO.specialist;

import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SpecialistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationPaginationRepository extends PagingAndSortingRepository<DatasetConfigurationEntity, Long> {

    Page<DatasetConfigurationEntity> findByDatasetEntityOrderByNameAsc(DatasetEntity datasetEntity, Pageable pageable);

    Page<DatasetConfigurationEntity> findByDatasetEntityAndSpecialistEntityOrderByNameAsc(DatasetEntity datasetEntity, SpecialistEntity specialistEntity, Pageable pageable);
}
