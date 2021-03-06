package com.services.specialist;

import com.DAO.specialist.ConfigurationRepository;
import com.DAO.specialist.DataSetPaginationRepository;
import com.DAO.specialist.DataSetRepository;
import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SpecialistEntity;
import com.models.specialist.DataSetPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataSetService {
    @Autowired
    DataSetRepository dataSetRepository;

    @Autowired
    DataSetPaginationRepository dataSetPaginationRepository;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    MagazineService magazineService;

    @Autowired
    DataSetConfigurationResultService dataSetConfigurationResultService;

    @Autowired
    ConfigurationRepository configurationRepository;

    @Autowired
    DataSetObjectsService dataSetObjectsService;

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
        dataSet.setColumns(datasetEntity.getColumns());

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

    public boolean deleteDataSet(SpecialistEntity specialistEntity, Long dataSetId) {
        DatasetEntity dataSet = dataSetRepository.findById(dataSetId).get();

        if (specialistEntity.getId() != dataSet.getSpecialistEntity().getId()) {
            return false;
        }

        List<DatasetConfigurationEntity> allConfigurations = configurationService.getAllConfigurationsByDataSetId(dataSetId);
        for (int i = 0; i < allConfigurations.size(); i++) {
            magazineService.removeConfiguration(allConfigurations.get(i));
            dataSetConfigurationResultService.removeAllResults(allConfigurations.get(i));
            configurationRepository.delete(allConfigurations.get(i));
        }

        dataSetObjectsService.removeDataSetObjects(dataSetId);
        dataSetRepository.delete(dataSet);

        return true;
    }

    public DatasetEntity getDataSetById(Long dataSetId) {
        return dataSetRepository.findById(dataSetId).get();
    }

    public List<DatasetEntity> getDataSetAll() {
        return dataSetRepository.findAllByOrderByNameAsc();
    }

    public DataSetPage getDataSetAllPage(int page, int objectsOnPage) {
        Page<DatasetEntity> dataSetpage = dataSetPaginationRepository.findAllByOrderByNameAsc(new PageRequest(--page, objectsOnPage));
        return new DataSetPage(dataSetpage.getTotalPages(), dataSetpage.getContent());
    }

    public List<DatasetEntity> getSpecialistDataSetAll(SpecialistEntity specialistEntity) {
        return dataSetRepository.findAllBySpecialistEntityOrderByNameAsc(specialistEntity);
    }

    public DataSetPage getSpecialistDataSetAllPage(SpecialistEntity specialistEntity, int page, int objectsOnPage) {
        Page<DatasetEntity> dataSetpage =
                dataSetPaginationRepository.findAllBySpecialistEntityOrderByNameAsc(specialistEntity, new PageRequest(--page, objectsOnPage));
        return new DataSetPage(dataSetpage.getTotalPages(), dataSetpage.getContent());
    }

    public List<DatasetEntity> getAllWorkDataSets() {
        ArrayList<DatasetEntity> allActiveConfigurationDataSets = new ArrayList<>();

        List<DatasetEntity> allActiveDataSets = dataSetRepository.findAllByActiveTrue();

        for (DatasetEntity dataSet : allActiveDataSets) {
            DatasetConfigurationEntity activeConfiguration = configurationService.findActiveConfiguration(dataSet);

            if (activeConfiguration != null) {
                dataSet.setId(activeConfiguration.getId());
                allActiveConfigurationDataSets.add(dataSet);
            }
        }
        return allActiveConfigurationDataSets;
    }
}
