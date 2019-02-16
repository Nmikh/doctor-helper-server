package com.models.specialist;

import com.models.entity.specialist.DatasetConfigurationEntity;

import java.io.Serializable;
import java.util.List;

public class ConfigurationPage implements Serializable {
    private int numberOfPages;
    private List<DatasetConfigurationEntity> datasetConfigurationEntities;

    public ConfigurationPage(int numberOfPages, List<DatasetConfigurationEntity> datasetConfigurationEntities) {
        this.numberOfPages = numberOfPages;
        this.datasetConfigurationEntities = datasetConfigurationEntities;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<DatasetConfigurationEntity> getDatasetConfigurationEntities() {
        return datasetConfigurationEntities;
    }

    public void setDatasetConfigurationEntities(List<DatasetConfigurationEntity> datasetConfigurationEntities) {
        this.datasetConfigurationEntities = datasetConfigurationEntities;
    }
}
