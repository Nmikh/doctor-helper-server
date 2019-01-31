package com.services.specialist;

import com.DAO.specialist.DataSetPaginationRepository;
import com.DAO.specialist.DataSetRepository;
import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SpecialistEntity;
import com.models.specialist.DataSetPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSetService {
    private final static int OBJECTS_ON_PAGE = 10;

    @Autowired
    DataSetRepository dataSetRepository;

    @Autowired
    DataSetPaginationRepository dataSetPaginationRepository;

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

    public DatasetEntity getDataSetById(Long dataSetId) {
        return dataSetRepository.findById(dataSetId).get();
    }

    public List<DatasetEntity> getDataSetAll() {
        return dataSetRepository.findAllByOrderByNameAsc();
    }

    public DataSetPage getDataSetAllPage(int page) {
        Page<DatasetEntity> dataSetpage = dataSetPaginationRepository.findAllByOrderByNameAsc(new PageRequest(--page, OBJECTS_ON_PAGE));
        return new DataSetPage(dataSetpage.getTotalPages(), dataSetpage.getContent());
    }

    public List<DatasetEntity> getSpecialistDataSetAll(SpecialistEntity specialistEntity) {
        return dataSetRepository.findAllBySpecialistEntityOrderByNameAsc(specialistEntity);
    }

    public DataSetPage getSpecialistDataSetAllPage(SpecialistEntity specialistEntity, int page) {
        Page<DatasetEntity> dataSetpage =
                dataSetPaginationRepository.findAllBySpecialistEntityOrderByNameAsc(specialistEntity, new PageRequest(--page, OBJECTS_ON_PAGE));
        return new DataSetPage(dataSetpage.getTotalPages(), dataSetpage.getContent());
    }

}
