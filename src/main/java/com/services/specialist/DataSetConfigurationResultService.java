package com.services.specialist;

import com.DAO.specialist.DataSetObjectsRepository;
import com.cache.HazelCastCache;
import com.conformal_predictors.DAO.ConfigurationResultRepository;
import com.conformal_predictors.DAO.ConfusionMatrixRepository;
import com.conformal_predictors.models.entity.ConfigurationResultEntity;
import com.conformal_predictors.models.entity.ConfusionMatrixEntity;
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

        while (hazelCastCache.getConfigurationGeneralResultMap().containsKey(newId)) {
            newId = hazelCastCache.getNewId();
        }

        hazelCastCache.getConfigurationGeneralResultMap().put(newId, 0);

        long finalNewId = newId;

        Thread thread = new Thread(() -> {
            confusionMatrixRepository.deleteAllByDatasetConfigurationEntity(configuration);
            configurationResultRepository.deleteAllByDatasetConfigurationEntity(configuration);

            List<ConfusionMatrixEntity> confusionMatrix = new ArrayList<>();

            for (int i = 0; i < SIGNIFICANCE.length; i++) {
                ConfusionMatrixEntity confusionMatrixEntity = new ConfusionMatrixEntity();

                confusionMatrixEntity.setEpsilon(SIGNIFICANCE[i]);
                confusionMatrixEntity.setDatasetConfigurationEntity(configuration);

                confusionMatrixEntity.setEmptyPredictions((long) 0);
                confusionMatrixEntity.setUncertainPredictions((long) 0);
                confusionMatrixEntity.setActivePredictedActive((long) 0);
                confusionMatrixEntity.setActivePredictedInactive((long) 0);
                confusionMatrixEntity.setInactivePredictedInactive((long) 0);
                confusionMatrixEntity.setInactivePredictedActive((long) 0);

                confusionMatrix.add(confusionMatrixEntity);
            }

            for (int i = 0; i < testDataSet.size(); i++) {
                ConfigurationResultEntity conformalPrediction
                        = symptomsService.getConformalPrediction(dataSet, testDataSet.get(i), configuration);
                conformalPrediction.setDatasetConfigurationEntity(configuration);
                conformalPrediction.setDatasetObjectsEntity(testDataSet.get(i));
                configurationResultRepository.save(conformalPrediction);

                for (int j = 0; j < SIGNIFICANCE.length; j++) {
                    if (conformalPrediction.getAlphaPositive() < SIGNIFICANCE[j]
                            && conformalPrediction.getAlphaNegative() < SIGNIFICANCE[j]) {
                        confusionMatrix.get(j).setEmptyPredictions(confusionMatrix.get(j).getEmptyPredictions() + 1);
                    } else if (conformalPrediction.getAlphaPositive() >= SIGNIFICANCE[j]
                            && conformalPrediction.getAlphaNegative() >= SIGNIFICANCE[j]) {
                        confusionMatrix.get(j).setUncertainPredictions(confusionMatrix.get(j).getUncertainPredictions() + 1);
                    } else if (conformalPrediction.getRealClass() == 1
                            && conformalPrediction.getPredictClass() == 1) {
                        confusionMatrix.get(j).setActivePredictedActive(confusionMatrix.get(j).getActivePredictedActive() + 1);
                    } else if (conformalPrediction.getRealClass() == -1
                            && conformalPrediction.getPredictClass() == -1) {
                        confusionMatrix.get(j).setInactivePredictedInactive(confusionMatrix.get(j).getInactivePredictedInactive() + 1);
                    } else if (conformalPrediction.getRealClass() == 1
                            && conformalPrediction.getPredictClass() == -1) {
                        confusionMatrix.get(j).setActivePredictedInactive(confusionMatrix.get(j).getActivePredictedInactive() + 1);
                    } else if (conformalPrediction.getRealClass() == -1
                            && conformalPrediction.getPredictClass() == 1) {
                        confusionMatrix.get(j).setInactivePredictedActive(confusionMatrix.get(j).getInactivePredictedActive() + 1);
                    }
                }

                hazelCastCache.getConfigurationGeneralResultMap().put(finalNewId, i * 100 / testDataSet.size());
            }

            for (int i = 0; i < SIGNIFICANCE.length; i++) {
                confusionMatrixRepository.save(confusionMatrix.get(i));
            }

            hazelCastCache.getConfigurationGeneralResultMap().put(finalNewId, testDataSet.size() / testDataSet.size() * 100);
        });

        thread.start();

        return finalNewId;
    }

    public Integer getConfigurationTestResult(long processId) {
        Integer percent = (Integer) hazelCastCache.getConfigurationGeneralResultMap().get(new Long(processId));

        if (percent == 100) {
            hazelCastCache.getConfigurationGeneralResultMap().remove(new Long(processId));
        }
        return percent;
    }

    public ConfigurationResultEntity getConfigurationResultSingle(Long configurationId, DatasetObjectsEntity datasetObject){
        DatasetConfigurationEntity configuration = configurationService.getConfigurationById(configurationId);
        List<DatasetObjectsEntity> dataSet = dataSetObjectsRepository.findByDatasetEntity(configuration.getDatasetEntity());

        long newId = hazelCastCache.getNewId();

        while (hazelCastCache.getConfigurationSingleResultMap().containsKey(newId)) {
            newId = hazelCastCache.getNewId();
        }

        hazelCastCache.getConfigurationGeneralResultMap().put(newId, 0);

        long finalNewId = newId;

        Thread thread = new Thread(() -> {
            ConfigurationResultEntity conformalPrediction
                    = symptomsService.getConformalPrediction((ArrayList<DatasetObjectsEntity>) dataSet, datasetObject, configuration);
        });
        thread.start();


        return symptomsService.getConformalPrediction((ArrayList<DatasetObjectsEntity>) dataSet, datasetObject, configuration);
    }
}
