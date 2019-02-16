package com.conformal_predictors.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetObjectsEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CONFIGURATION_RESULT")
public class ConfigurationResultEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name = "REAL_CLASS", nullable = true)
    private Long realClass;

    @Basic
    @Column(name = "P_POSITIVE", nullable = true, precision = 0)
    private Double pPositive;

    @Basic
    @Column(name = "P_NEGATIVE", nullable = true, precision = 0)
    private Double pNegative;

    @Basic
    @Column(name = "PREDICT_CLASS", nullable = true)
    private Long predictClass;

    @Basic
    @Column(name = "CONFIDENCE", nullable = true, precision = 0)
    private Double confidence;

    @Basic
    @Column(name = "CREDIBILITY", nullable = true, precision = 0)
    private Double credibility;

    @Basic
    @Column(name = "ALPHA_POSITIVE", nullable = true, precision = 0)
    private Double alphaPositive;

    @Basic
    @Column(name = "ALPHA_NEGATIVE", nullable = true, precision = 0)
    private Double alphaNegative;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONFIGURATION_ID")
    private DatasetConfigurationEntity datasetConfigurationEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DATASET_OBJECT_ID")
    private DatasetObjectsEntity datasetObjectsEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getRealClass() {
        return realClass;
    }

    public void setRealClass(Long realClass) {
        this.realClass = realClass;
    }

    public Double getpPositive() {
        return pPositive;
    }

    public void setpPositive(Double pPositive) {
        this.pPositive = pPositive;
    }

    public Double getpNegative() {
        return pNegative;
    }

    public void setpNegative(Double pNegative) {
        this.pNegative = pNegative;
    }

    public Long getPredictClass() {
        return predictClass;
    }

    public void setPredictClass(Long predictClass) {
        this.predictClass = predictClass;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Double getCredibility() {
        return credibility;
    }

    public void setCredibility(Double credibility) {
        this.credibility = credibility;
    }

    public Double getAlphaPositive() {
        return alphaPositive;
    }

    public void setAlphaPositive(Double alphaPositive) {
        this.alphaPositive = alphaPositive;
    }

    public Double getAlphaNegative() {
        return alphaNegative;
    }

    public void setAlphaNegative(Double alphaNegative) {
        this.alphaNegative = alphaNegative;
    }

    public DatasetConfigurationEntity getDatasetConfigurationEntity() {
        return datasetConfigurationEntity;
    }

    public void setDatasetConfigurationEntity(DatasetConfigurationEntity datasetConfigurationEntity) {
        this.datasetConfigurationEntity = datasetConfigurationEntity;
    }

    public DatasetObjectsEntity getDatasetObjectsEntity() {
        return datasetObjectsEntity;
    }

    public void setDatasetObjectsEntity(DatasetObjectsEntity datasetObjectsEntity) {
        this.datasetObjectsEntity = datasetObjectsEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigurationResultEntity that = (ConfigurationResultEntity) o;

        if (id != that.id) return false;
        if (realClass != null ? !realClass.equals(that.realClass) : that.realClass != null) return false;
        if (pPositive != null ? !pPositive.equals(that.pPositive) : that.pPositive != null) return false;
        if (pNegative != null ? !pNegative.equals(that.pNegative) : that.pNegative != null) return false;
        if (predictClass != null ? !predictClass.equals(that.predictClass) : that.predictClass != null) return false;
        if (confidence != null ? !confidence.equals(that.confidence) : that.confidence != null) return false;
        if (credibility != null ? !credibility.equals(that.credibility) : that.credibility != null) return false;
        if (alphaPositive != null ? !alphaPositive.equals(that.alphaPositive) : that.alphaPositive != null)
            return false;
        if (alphaNegative != null ? !alphaNegative.equals(that.alphaNegative) : that.alphaNegative != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (realClass != null ? realClass.hashCode() : 0);
        result = 31 * result + (pPositive != null ? pPositive.hashCode() : 0);
        result = 31 * result + (pNegative != null ? pNegative.hashCode() : 0);
        result = 31 * result + (predictClass != null ? predictClass.hashCode() : 0);
        result = 31 * result + (confidence != null ? confidence.hashCode() : 0);
        result = 31 * result + (credibility != null ? credibility.hashCode() : 0);
        result = 31 * result + (alphaPositive != null ? alphaPositive.hashCode() : 0);
        result = 31 * result + (alphaNegative != null ? alphaNegative.hashCode() : 0);
        return result;
    }
}
