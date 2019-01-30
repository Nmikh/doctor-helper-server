package com.models.entity.specialist;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DATASET_CONFIGURATION_MAGAZINE")
public class DatasetConfigurationMagazineEntity {
    @Id
    @Column(name = "ID")
    private long id;

    @Basic
    @Column(name = "DATE_TIME")
    private Timestamp dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONFIGURATION_ID_BEFORE")
    private DatasetConfigurationEntity datasetConfigurationBefore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONFIGURATION_ID_AFTER")
    private DatasetConfigurationEntity datasetConfigurationAfter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SPECIALIST_ID")
    private SpecialistEntity specialistEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public DatasetConfigurationEntity getDatasetConfigurationBefore() {
        return datasetConfigurationBefore;
    }

    public void setDatasetConfigurationBefore(DatasetConfigurationEntity datasetConfigurationBefore) {
        this.datasetConfigurationBefore = datasetConfigurationBefore;
    }

    public DatasetConfigurationEntity getDatasetConfigurationAfter() {
        return datasetConfigurationAfter;
    }

    public void setDatasetConfigurationAfter(DatasetConfigurationEntity datasetConfigurationAfter) {
        this.datasetConfigurationAfter = datasetConfigurationAfter;
    }

    public SpecialistEntity getSpecialistEntity() {
        return specialistEntity;
    }

    public void setSpecialistEntity(SpecialistEntity specialistEntity) {
        this.specialistEntity = specialistEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatasetConfigurationMagazineEntity that = (DatasetConfigurationMagazineEntity) o;
        return id == that.id &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime);
    }
}
