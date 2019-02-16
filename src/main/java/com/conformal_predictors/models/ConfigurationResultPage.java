package com.conformal_predictors.models;

import com.conformal_predictors.models.entity.ConfigurationResultEntity;

import java.io.Serializable;
import java.util.List;

public class ConfigurationResultPage implements Serializable {
    private int numberOfPages;
    private List<ConfigurationResultEntity> configurationResultEntities;

    public ConfigurationResultPage(int numberOfPages, List<ConfigurationResultEntity> configurationResultEntities) {
        this.numberOfPages = numberOfPages;
        this.configurationResultEntities = configurationResultEntities;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<ConfigurationResultEntity> getConfigurationResultEntities() {
        return configurationResultEntities;
    }

    public void setConfigurationResultEntities(List<ConfigurationResultEntity> configurationResultEntities) {
        this.configurationResultEntities = configurationResultEntities;
    }
}
