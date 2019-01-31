package com.DAO.specialist;

import com.models.entity.specialist.DatasetConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<DatasetConfigurationEntity, Long> {
}
