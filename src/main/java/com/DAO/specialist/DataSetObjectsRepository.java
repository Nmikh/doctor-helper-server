package com.DAO.specialist;

import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.DatasetObjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface DataSetObjectsRepository extends JpaRepository<DatasetObjectsEntity, Long> {

    @Override
    <S extends DatasetObjectsEntity> List<S> saveAll(Iterable<S> iterable);

    List<DatasetObjectsEntity> findByDatasetEntity(DatasetEntity datasetEntity);

    @Transactional
    void deleteDatasetObjectsEntitiesByDatasetEntity(DatasetEntity datasetEntity);
}
