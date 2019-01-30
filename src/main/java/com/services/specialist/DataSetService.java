package com.services.specialist;

import com.DAO.specialist.DataSetRepository;
import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SpecialistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSetService {
    @Autowired
    DataSetRepository dataSetRepository;

    public Long createDataSet(DatasetEntity datasetEntity, SpecialistEntity specialistEntity) {
        datasetEntity.setActive(false);
        datasetEntity.setSpecialistEntity(specialistEntity);

        DatasetEntity dataSetSave = dataSetRepository.save(datasetEntity);

        return dataSetSave.getId();
    }

    public boolean changeDataSet(DatasetEntity datasetEntity, SpecialistEntity specialistEntity, Long dataSetId) {
        DatasetEntity dataSet = dataSetRepository.findById(dataSetId).get();

        if (specialistEntity.getId() != dataSet.getSpecialistEntity().getId()) {
            return false;
        }

        dataSet.setName(datasetEntity.getName());
        dataSet.setDescription(datasetEntity.getDescription());

        dataSetRepository.save(dataSet);
        return true;
    }

    public boolean activateDataSet(SpecialistEntity specialistEntity, Long dataSetId, Boolean dataSetActivate) {
        DatasetEntity dataSet = dataSetRepository.findById(dataSetId).get();

        if (specialistEntity.getId() != dataSet.getSpecialistEntity().getId()) {
            return false;
        }

        dataSet.setActive(dataSetActivate);

        dataSetRepository.save(dataSet);
        return true;
    }
}
