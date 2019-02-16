package com.services.specialist;

import com.DAO.specialist.DataSetObjectsRepository;
import com.DAO.specialist.SpecialistRepository;
import com.conformal_predictors.DAO.ConfigurationResultRepository;
import com.conformal_predictors.DAO.ConfusionMatrixRepository;
import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.DatasetObjectsEntity;
import com.models.entity.specialist.SpecialistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataSetObjectsService {

    @Autowired
    SpecialistService specialistService;

    @Autowired
    DataSetService dataSetService;

    @Autowired
    DataSetObjectsRepository dataSetObjectsRepository;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    ConfigurationResultRepository configurationResultRepository;

    @Autowired
    ConfusionMatrixRepository confusionMatrixRepository;

    public String getDatSet(Long dataSetId) {
        DatasetEntity dataSet = dataSetService.getDataSetById(dataSetId);
        List<DatasetObjectsEntity> objects = dataSetObjectsRepository.findByDatasetEntity(dataSet);

        String result = "";
        for (int i = 0; i < objects.size(); i++) {
            result = result + objects.get(i).getId() + ","
                    + objects.get(i).getObjectClass() + ","
                    + objects.get(i).getParams() + "\r\n";
        }

        return result;
    }

    public void removeDataSetObjects(Long dataSetId) {
        DatasetEntity dataSet = dataSetService.getDataSetById(dataSetId);
        List<DatasetObjectsEntity> objects = dataSetObjectsRepository.findByDatasetEntity(dataSet);
        dataSetObjectsRepository.deleteAll(objects);
    }

    public boolean addDataObjectsToDataSet(MultipartFile file, SpecialistEntity specialistEntity, Long dataSetId) throws IOException {
        DatasetEntity dataSet = dataSetService.getDataSetById(dataSetId);

        if (specialistEntity.getId() != dataSet.getSpecialistEntity().getId()) {
            return false;
        }

        List<DatasetConfigurationEntity> allConfigurationsByDataSet = configurationService.getAllConfigurationsByDataSetId(dataSetId);
        for (int i = 0; i < allConfigurationsByDataSet.size(); i++) {
            confusionMatrixRepository.deleteAllByDatasetConfigurationEntity(allConfigurationsByDataSet.get(i));
            configurationResultRepository.deleteAllByDatasetConfigurationEntity(allConfigurationsByDataSet.get(i));
        }

        dataSetObjectsRepository.deleteDatasetObjectsEntitiesByDatasetEntity(dataSet);

        List<DatasetObjectsEntity> dataSetObjectsFromFile = getDataSetObjectsFromFile(file, dataSet);
        dataSetObjectsRepository.saveAll(dataSetObjectsFromFile);

        return true;
    }

    public List<DatasetObjectsEntity> getDataSetObjectsFromFile(MultipartFile file, DatasetEntity dataSet) throws IOException {
        ArrayList<DatasetObjectsEntity> dataSetObjectsEntities = new ArrayList<>();

        List<String> strings = Arrays.asList(new String(file.getBytes()).split("\r\n"));

        for (String object : strings) {
            DatasetObjectsEntity datasetObjectsEntity = new DatasetObjectsEntity();

            String[] splitObject = object.split("\\,", 3);

            datasetObjectsEntity.setObjectClass(Long.parseLong(splitObject[1]));
            datasetObjectsEntity.setParams(splitObject[2]);
            datasetObjectsEntity.setDatasetEntity(dataSet);

            dataSetObjectsEntities.add(datasetObjectsEntity);
        }

        return dataSetObjectsEntities;
    }
}
