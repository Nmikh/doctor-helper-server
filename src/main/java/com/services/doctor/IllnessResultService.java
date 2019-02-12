package com.services.doctor;

import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetEntity;
import com.services.specialist.ConfigurationService;
import com.services.specialist.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IllnessResultService {
    @Autowired
    DataSetService dataSetService;

    @Autowired
    ConfigurationService configurationService;

    public List<DatasetEntity> getAllWorkDataSets() {
        ArrayList<DatasetEntity> allActiveConfigurationDataSets = new ArrayList<>();

        List<DatasetEntity> allActiveDataSets = dataSetService.findAllActiveDataSets();

        for (DatasetEntity dataSet: allActiveDataSets) {
            DatasetConfigurationEntity activeConfiguration = configurationService.findActiveConfiguration(dataSet);

            if (activeConfiguration != null) {
                allActiveConfigurationDataSets.add(dataSet);
            }
        }
        return allActiveConfigurationDataSets;
    }
}
