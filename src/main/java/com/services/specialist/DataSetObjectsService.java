package com.services.specialist;

import com.DAO.specialist.DataSetObjectsRepository;
import com.DAO.specialist.SpecialistRepository;
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

    public boolean addDataObjectsToDataSet(MultipartFile file, SpecialistEntity specialistEntity, Long dataSetId) throws IOException {
        DatasetEntity dataSet = dataSetService.getDataSetById(dataSetId);

        if (specialistEntity.getId() != dataSet.getSpecialistEntity().getId()) {
            return false;
        }

        List<DatasetObjectsEntity> dataSetObjectsFromFile = getDataSetObjectsFromFile(file, dataSet);

        dataSetObjectsRepository.saveAll(dataSetObjectsFromFile);

        return true;
    }

    private List<DatasetObjectsEntity> getDataSetObjectsFromFile(MultipartFile file, DatasetEntity dataSet) throws IOException {
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
