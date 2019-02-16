package com.models.specialist;

import com.models.entity.specialist.DatasetEntity;

import java.io.Serializable;
import java.util.List;

public class DataSetPage implements Serializable {
    private int numberOfPages;
    private List<DatasetEntity> datasetEntities;

    public DataSetPage(int numberOfPages, List<DatasetEntity> datasetEntities) {
        this.numberOfPages = numberOfPages;
        this.datasetEntities = datasetEntities;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<DatasetEntity> getDatasetEntities() {
        return datasetEntities;
    }

    public void setDatasetEntities(List<DatasetEntity> datasetEntities) {
        this.datasetEntities = datasetEntities;
    }
}
