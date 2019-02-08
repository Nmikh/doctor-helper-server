package com.DAO.specialist;

import com.models.entity.specialist.DatasetConfigurationMagazineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MagazineRepository extends JpaRepository<DatasetConfigurationMagazineEntity, Long> {

    @Query(value = "SELECT DATASET_CONFIGURATION_MAGAZINE.ID " +
            "FROM (DATASET_CONFIGURATION_MAGAZINE " +
            "  inner join DATASET_CONFIGURATION " +
            "  on DATASET_CONFIGURATION_MAGAZINE.CONFIGURATION_ID_AFTER = DATASET_CONFIGURATION.ID) " +
            "       inner join DATASET " +
            "                  on DATASET_CONFIGURATION.DATASET_ID = DATASET.ID " +
            "where DATASET.ID = ?1 " +
            "order by DATASET_CONFIGURATION_MAGAZINE.DATE_TIME DESC", nativeQuery = true)
    List findMagazineId(Long id);

    @Override
    Optional<DatasetConfigurationMagazineEntity> findById(Long aLong);
}
