package com.DAO.specialist;

import com.models.entity.specialist.DatasetConfigurationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationPaginationRepository extends PagingAndSortingRepository<DatasetConfigurationEntity, Long> {
}
