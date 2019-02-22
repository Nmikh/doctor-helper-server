package com.DAO.specialist;

import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetConfigurationMagazineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MagazineRepository extends JpaRepository<DatasetConfigurationMagazineEntity, Long> {

    @Query(value = "SELECT DATASET_CONFIGURATION_MAGAZINE.ID " +
            "FROM (DATASET_CONFIGURATION_MAGAZINE " +
            "  INNER JOIN DATASET_CONFIGURATION " +
            "  ON DATASET_CONFIGURATION_MAGAZINE.CONFIGURATION_ID_AFTER = DATASET_CONFIGURATION.ID) " +
            "       INNER JOIN DATASET " +
            "                  ON DATASET_CONFIGURATION.DATASET_ID = DATASET.ID " +
            "WHERE DATASET.ID = ?1 " +
            "ORDER BY DATASET_CONFIGURATION_MAGAZINE.DATE_TIME DESC", nativeQuery = true)
    List findMagazineId(Long id);

    @Override
    Optional<DatasetConfigurationMagazineEntity> findById(Long aLong);

    @Query(value = "SELECT DATASET_CONFIGURATION_MAGAZINE.ID " +
            "FROM DATASET_CONFIGURATION_MAGAZINE " +
            "WHERE DATASET_CONFIGURATION_MAGAZINE.CONFIGURATION_ID_BEFORE = ?1 ", nativeQuery = true)
    List findMagazineIdByConfigurationBefore(Long idAfter);

    @Query(value = "SELECT DATASET_CONFIGURATION_MAGAZINE.ID " +
            "FROM DATASET_CONFIGURATION_MAGAZINE " +
            "WHERE DATASET_CONFIGURATION_MAGAZINE.CONFIGURATION_ID_AFTER = ?1 ", nativeQuery = true)
    List findMagazineIdByConfigurationAfter(Long id);

}
