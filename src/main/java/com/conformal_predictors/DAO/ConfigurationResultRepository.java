package com.conformal_predictors.DAO;

import com.conformal_predictors.models.entity.ConfigurationResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationResultRepository extends JpaRepository<ConfigurationResultEntity, Long> {
}
