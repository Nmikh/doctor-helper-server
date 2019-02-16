package com.models.specialist;

import com.conformal_predictors.models.entity.ConfigurationResultEntity;
import com.conformal_predictors.models.entity.ConfusionMatrixEntity;

import java.io.Serializable;
import java.util.List;

public class ConfigurationPartResult implements Serializable{
    private long status;
    private List<ConfusionMatrixEntity> confusionMatrix;
    private List<ConfigurationResultEntity> configurationResult;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public List<ConfusionMatrixEntity> getConfusionMatrix() {
        return confusionMatrix;
    }

    public void setConfusionMatrix(List<ConfusionMatrixEntity> confusionMatrix) {
        this.confusionMatrix = confusionMatrix;
    }

    public List<ConfigurationResultEntity> getConfigurationResult() {
        return configurationResult;
    }

    public void setConfigurationResult(List<ConfigurationResultEntity> configurationResult) {
        this.configurationResult = configurationResult;
    }
}
