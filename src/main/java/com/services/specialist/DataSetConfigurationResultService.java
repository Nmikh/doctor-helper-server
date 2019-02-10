package com.services.specialist;

import com.DAO.specialist.DataSetObjectsRepository;
import com.cache.HazelCastCache;
import com.conformal_predictors.DAO.ConfigurationResultRepository;
import com.conformal_predictors.DAO.ConfusionMatrixRepository;
import com.conformal_predictors.models.entity.ConfigurationResultEntity;
import com.conformal_predictors.services.SymptomsService;
import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetObjectsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataSetConfigurationResultService {
    private final static Double[] SIGNIFICANCE = {0.01, 0.05, 0.1, 0.15, 0.2};

    @Autowired
    SymptomsService symptomsService;

    @Autowired
    HazelCastCache hazelCastCache;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    DataSetObjectsRepository dataSetObjectsRepository;

    @Autowired
    ConfigurationResultRepository configurationResultRepository;

    @Autowired
    ConfusionMatrixRepository confusionMatrixRepository;

    public long startConfigurationTest(Long configurationId) {
        DatasetConfigurationEntity configuration = configurationService.getConfigurationById(configurationId);
        List<DatasetObjectsEntity> objects = dataSetObjectsRepository.findByDatasetEntity(configuration.getDatasetEntity());

        ArrayList<DatasetObjectsEntity> dataSet = new ArrayList<>();
        ArrayList<DatasetObjectsEntity> testDataSet = new ArrayList<>();

        int testSize = (int) (objects.size() * configuration.getTestPart());

        for (int i = 0; i < objects.size() - testSize; i++) {
            dataSet.add(objects.get(i));
        }

        for (int i = objects.size() - testSize; i < objects.size(); i++) {
            testDataSet.add(objects.get(i));
        }

        long newId = hazelCastCache.getNewId();

        while (hazelCastCache.getConfigurationResultMap().containsKey(newId)) {
            newId = hazelCastCache.getNewId();
        }

        hazelCastCache.getConfigurationResultMap().put(newId, 0);

        long finalNewId = newId;

        Thread thread = new Thread(() -> {
            for (int i = 0; i < testDataSet.size(); i++) {
                ConfigurationResultEntity conformalPrediction
                        = symptomsService.getConformalPrediction(dataSet, testDataSet.get(i), configuration);
                conformalPrediction.setDatasetConfigurationEntity(configuration);
                conformalPrediction.setDatasetObjectsEntity(testDataSet.get(i));
                configurationResultRepository.save(conformalPrediction);

                hazelCastCache.getConfigurationResultMap().put(finalNewId, i * 100 / (testDataSet.size() - 1));
            }
        });

        thread.start();

        return finalNewId;
    }

    public Integer getConfigurationTestResult(long processId) {
        Integer percent = (Integer) hazelCastCache.getConfigurationResultMap().get(new Long(processId));

        if (percent == 100) {
            hazelCastCache.getConfigurationResultMap().remove(new Long(processId));
        }
        return percent;
    }
}
