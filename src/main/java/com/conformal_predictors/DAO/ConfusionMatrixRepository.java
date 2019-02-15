package com.conformal_predictors.DAO;

import com.conformal_predictors.models.entity.ConfusionMatrixEntity;
import com.models.entity.specialist.DatasetConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ConfusionMatrixRepository extends JpaRepository<ConfusionMatrixEntity, Long> {
    @Transactional
    void deleteAllByDatasetConfigurationEntity(DatasetConfigurationEntity datasetConfigurationEntity);

    List<ConfusionMatrixEntity> findByDatasetConfigurationEntityOrderByEpsilonAsc(DatasetConfigurationEntity datasetConfigurationEntity);
}
