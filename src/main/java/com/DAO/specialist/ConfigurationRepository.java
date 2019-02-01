package com.DAO.specialist;

import com.models.entity.specialist.DatasetConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<DatasetConfigurationEntity, Long> {
    @Override
    Optional<DatasetConfigurationEntity> findById(Long aLong);
}
