package com.conformal_predictors.DAO;

import com.conformal_predictors.models.entity.ConfusionMatrixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfusionMatrixRepository extends JpaRepository<ConfusionMatrixEntity, Long> {
}
